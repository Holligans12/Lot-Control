package Image.To.Text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class List_Line extends AppCompatActivity {
    private Button button_mainassy, button_cover, button_holder, button_stator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_line);
        button_mainassy = (Button) findViewById(R.id.button_mainassy);
        button_cover = (Button) findViewById(R.id.button_cover);
        button_holder = (Button) findViewById(R.id.button_holder);
        button_stator = (Button) findViewById(R.id.button_stator);

        button_holder.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMaterial_Holder = new Intent(getApplicationContext(), Material_Holder.class);
                startActivity(openMaterial_Holder);
            }
        }));


    }
}