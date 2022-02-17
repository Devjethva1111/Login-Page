package com.monstertechno.loginsignupui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText edt1, edt2, edt3;
    FloatingActionButton floatingActionButton;
    TextView txt1;
    boolean isEmail, isNameValid, ispassword;
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
        setContentView(R.layout.activity_signup);

        edt1 = findViewById(R.id.Namelogin);
        edt2 = findViewById(R.id.Emaillogin);
        edt3 = findViewById(R.id.Passwordlogin);
        txt1 = findViewById(R.id.signin_page);
        floatingActionButton = findViewById(R.id.page_signup);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validatePassword();

                if (edt1.getText().toString().isEmpty()) {
                    edt1.setError(getResources().getString(R.string.name_error));
                    isNameValid = false;
                } else  {
                    isNameValid = true;
                }

                // Check for a valid phone number.
                if (edt2.getText().toString().isEmpty()) {
                    edt2.setError(getResources().getString(R.string.email_error));
                    isEmail = false;
                } else  {
                    isEmail = true;
                }

                if (isEmail && ispassword && isNameValid) {
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
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
        String passwordInput = edt3.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            edt3.setError("Field can't be empty");
            ispassword =  false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            edt3.setError("Password too weak");
            ispassword =  false;
        } else {
            edt3.setError(null);
            ispassword =  true;
        }
        return true;
    }
}
