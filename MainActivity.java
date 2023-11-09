package Image.To.Text;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button_capture, btn_scan, btn_send, btn_got;
    TextView textview_data, text_data, show, show2;
    Bitmap bitmap;
    private static final int REQUEST_CAMERA_CODE=100;
    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Date & Time");
        TextView textViewDate = findViewById(R.id.Date);
        textViewDate.setText(getDate());
        TextView Clock = findViewById(R.id.clock);
        TextView nameTxt = findViewById(R.id.textpn);
        show = findViewById(R.id.textpic);
        show2 = findViewById(R.id.textnpk);
        text_data = findViewById(R.id.text_data);
        btn_got = findViewById(R.id.get);
        btn_scan=findViewById(R.id.btn_scan);
        btn_send=findViewById(R.id.btn_send);
        btn_scan.setOnClickListener(v->
        {    scanCode();
        });

        ConSQL c= new ConSQL();
        connection = c.conclass();
        try {
            if(c != null){
                String sqlstatement="select * from Username";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sqlstatement);
                while (rs.next()){
                    show.setText(rs.getString(2 ));
                    show2.setText(rs.getString(3));
                }

            }
        }
        catch (Exception exception) {
            Log.e ("Error", exception.getMessage());
        }

        button_capture = findViewById(R.id.button_capture);
        //button_copy = findViewById(R.id.button_copy);
        textview_data = findViewById(R.id.text_data);


        String username = "username not set";
        Bundle extrast = getIntent().getExtras();
        if (extrast != null) {
            username = extrast.getString("textviewpngrommet");
        }
        {
            nameTxt.setText(username);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);

        }

        btn_got.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConSQL c= new ConSQL();
                connection = c.conclass();
                try {
                    if(c != null){
                        String sqlstatement="select * from Username";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlstatement);
                        while (rs.next()){
                            show.setText(rs.getString(2 ));
                            show2.setText(rs.getString(3));
                        }

                    }
                }
                catch (Exception exception) {
                    Log.e ("Error", exception.getMessage());
                }
            }
        });


        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(MainActivity.this);

            }
        });

        //button_copy.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                // scanned_text = textview_data.getText().toString();
                //copyToClipBoard(scanned_text);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConSQL c= new ConSQL();
                connection = c.conclass();
                try {
                    if(c != null){
                        String sqlstatement="insert into daftar_lot_numbers (part_number,lot_number,tanggal,jam,PIC,NPK) values ('"+nameTxt.getText().toString()+"','"+text_data.getText().toString()+"','"+textViewDate.getText().toString()+"','"+Clock.getText().toString()+"','"+show.getText().toString()+"','"+show2.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlstatement);

                        while (rs.next()){
                            text_data.setText(rs.getString(3 ));
                        }

                    }
                }
                catch (Exception exception) {
                    Log.e ("Error", exception.getMessage());
                }
            }
        });
            }
    private String getDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    getTextFromImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private void getTextFromImage(Bitmap bitmap){
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()){
            Toast.makeText(MainActivity.this, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<textBlockSparseArray.size();i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            textview_data.setText(stringBuilder.toString());
            button_capture.setText("Retake");
            //button_copy.setVisibility(View.VISIBLE);

        }
    }

    private void copyToClipBoard(String text){
        ClipboardManager clipBoard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data", text);
        clipBoard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied to clipboard!", Toast.LENGTH_SHORT).show();
    }


    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume Up to Flash On");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }




    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents() !=null)
        {
            text_data.setText(result.getContents());

        }
    });}