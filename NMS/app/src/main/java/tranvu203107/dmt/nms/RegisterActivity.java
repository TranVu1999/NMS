package tranvu203107.dmt.nms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    String DATABASE_NAME="myDB.sqlite";
    SQLiteDatabase database = null;
    Button btnRegister;
    EditText txtEmail;
    EditText txtName;
    EditText txtPassword;
    EditText txtConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        addEvents();
    }

    public void changeStatusBarColor(){
        // for changing  status bar icon color
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));

        }
    }
    // Xu ly login
    private void addControls() {
        btnRegister = (Button) findViewById(R.id.btnChange);
        txtEmail = (EditText) findViewById(R.id.txtEmailChange);
        txtName = (EditText) findViewById(R.id.txtNameChange) ;
        txtPassword = (EditText)findViewById((R.id.txtPasswordRegister));
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
    }
    private void addEvents() {
        addControls();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addControls();
                database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
                ContentValues values = new ContentValues();
                values.put("name",txtName.getText().toString());
                values.put("email",txtEmail.getText().toString());
                values.put("password",txtPassword.getText().toString());
                if(txtPassword.getText().toString().isEmpty()||txtEmail.getText().toString().isEmpty()||txtPassword.getText().toString().isEmpty()
                        ||txtConfirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ALl field must be fill!", Toast.LENGTH_LONG).show();
                }
                else if(txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString())== false){
                    Toast.makeText(RegisterActivity.this, "Password and ConfirmPassword must match!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    long kq = database.insert("USER",null, values);
                    database.close();
                    if(kq>0) {
                        Toast.makeText(RegisterActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                    }
                    else
                        Toast.makeText(RegisterActivity.this,"Thêm thất bại",Toast.LENGTH_LONG).show();
                }}
        });
    }
    public void onLoginClick(View v){
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }
}