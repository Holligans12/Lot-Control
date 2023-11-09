package Image.To.Text;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.checkinternet.Utility.NetworkChangeListener;
import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class login_page extends AppCompatActivity {
    private com.google.android.material.button.MaterialButton loginbtn;
    Connection connection;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView username=(TextView) findViewById(R.id.username);
        TextView npk=(TextView) findViewById(R.id.npk);
        MaterialButton loginbtn= (MaterialButton) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConSQL c= new ConSQL();
                connection = c.conclass();
                try {
                    if(c != null){
                        String sqlstatement="insert into Username (Username,NPK) values ('"+username.getText().toString()+"','"+npk.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlstatement);
                        }

                    }
                catch (Exception exception) {
                    Log.e ("Error", exception.getMessage());
                }
                openlist_line();

            }
        });
    }
    public void openlist_line(){
        Intent intent = new Intent(this, List_Line.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}