package Image.To.Text;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConSQL {
    Connection con;
    @SuppressLint("NewApi")
    public Connection conclass()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectURL = null;

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL="jdbc:jtds:sqlserver://10.73.142.60:1433;databasename=db_Material_Lot_Control;user=sa;password=sqladmin;";
            connection = DriverManager.getConnection(ConnectURL);
            con=DriverManager.getConnection(ConnectURL);
        }
        catch (Exception e)
        {
            Log.e("Error ", e.getMessage());
        }
        return connection;
    }
}
