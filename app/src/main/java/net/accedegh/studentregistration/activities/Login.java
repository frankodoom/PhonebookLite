package net.accedegh.studentregistration.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.accedegh.studentregistration.R;

public class Login extends AppCompatActivity {

    private TextView register;
    private EditText email, password;
    private Button btnLogin;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);

       register = findViewById(R.id.txtRegister);
       register.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent = new Intent(Login.this, Register.class);
           startActivity(intent);
       }
   });


   btnLogin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           preferences  = getSharedPreferences("UserInfo",MODE_PRIVATE);
           String _email =    email.getText().toString().trim();
           String _password = password.getText().toString().trim();
           String prefEmail =  preferences.getString("Email",null);
           String prefPassword = preferences.getString("Password",null);
           
           if(_email.equals(prefEmail) && _password.equals(prefPassword)){
               Intent intent = new  Intent(Login.this,ConatctsList.class);
               startActivity(intent);
               Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
           }
           else
               {
               Toast.makeText(Login.this, "invalid credentials", Toast.LENGTH_SHORT).show();
           }
            
       }
   });
   
    }
}
