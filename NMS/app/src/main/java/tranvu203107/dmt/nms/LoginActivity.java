package tranvu203107.dmt.nms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

//import tranvu203107.dmt.nms.model.USER;

public class LoginActivity extends AppCompatActivity {
    String DATABASE_NAME="myDB.sqlite";
    String DB_PATH_SUFFIX="/databases/";
    SQLiteDatabase database = null;
    Button btnLogin;
    EditText txtEmail;
    EditText txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load database
        processCopy();
        // Handling login
        // addControls();

        // for changing  status bar icon color
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        addEvents();
    }
    // Xu ly
    private void addControls() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEmail = (EditText) findViewById(R.id.txtEmailRegister);
        txtPassword = (EditText)findViewById((R.id.txtPasswordLogin));
    }
    private void addEvents() {
        addControls();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
                String query = "select * from USER where Email = '" + txtEmail.getText().toString() + "' and Password = '" + txtPassword.getText().toString() +"'";
                Cursor cursor = null;
                cursor   = database.rawQuery(query,null);
                if(txtPassword.getText().toString().isEmpty()||txtEmail.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "All field must be fill!", Toast.LENGTH_LONG).show();
                }
                else if(cursor.getCount() == 1 ) {
                    int Id;
                    //USER user =new USER();
                    // get id's user.
                    cursor.moveToFirst();
                    Id = cursor.getInt(0);
                    Toast.makeText(LoginActivity.this, "Successfully login!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class).putExtra("Id", Id);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid incredent!", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        });
    }
    public void onLoginClick(View v){
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    // Dataabase
    private void processCopy()
    {
        try {
            File dbFile = getDatabasePath(DATABASE_NAME);
            if (!dbFile.exists()) {
                copyDatabaseFromAsset();
                Toast.makeText(LoginActivity.this, "Sao chép thành công", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(LoginActivity.this,"Khong sao chep",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(LoginActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            Log.e("LOI",ex.toString());
        }
    }
    private String getDatabasePath()
    {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }
    private void copyDatabaseFromAsset() {
        try{
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())
            {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte []buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer))>0)
            {
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();


        }catch (Exception ex)
        {
            Log.e("LOI",ex.toString());
        }

    }
}