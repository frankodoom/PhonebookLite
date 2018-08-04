package net.accedegh.studentregistration.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.accedegh.studentregistration.R;

public class Register extends AppCompatActivity {

    EditText name, email, password, confirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.reg_fullName);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        confirmPassword = findViewById(R.id.reg_confirmPassword);
        btnRegister = findViewById(R.id.reg_Save);

       // password.setTransformationMethod();

        SharedPreferences preferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _name = name.getText().toString();
                String _email = email.getText().toString();
                String _password =password.getText().toString();
                String _confirmPassword = confirmPassword.getText().toString();

                editor.putString("Name", _name);
                editor.putString("Email", _email);
                editor.putString("Password",_password);
                boolean result = editor.commit();
                if(result==true){
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }

                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
