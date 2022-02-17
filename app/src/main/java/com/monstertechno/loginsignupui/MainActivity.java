package com.monstertechno.loginsignupui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edt1, edt2;
    TextView txt1, text2;
    FloatingActionButton floatingActionButton;
    boolean isEmail, ispassword;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.Emaillogin);
        edt2 = findViewById(R.id.Passwordlogin);
        txt1 = findViewById(R.id.signup_page);
        floatingActionButton =findViewById(R.id.button_signin);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this , SignupActivity.class);
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validatePassword();

                if (edt1.getText().toString().isEmpty()) {
                    edt1.setError(getResources().getString(R.string.email_error));
                    isEmail = false;
                } else  {
                    isEmail = true;
                }


                if (isEmail && ispassword) {
                    Intent i = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validatePassword() {
        String passwordInput = edt2.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            edt2.setError("Field can't be empty");
            ispassword =  false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            edt2.setError("Password too weak");
            ispassword =  false;
        } else {
            edt2.setError(null);
            ispassword =  true;
        }
        return true;
    }

}
