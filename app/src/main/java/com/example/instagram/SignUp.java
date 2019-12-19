package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText edtUsernameSignUp, edtUserNameLogin,edtUserNamePassword,edtPasswordLogin;
    private Button BtnsignUp, BtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        edtUsernameSignUp = findViewById(R.id.edtUsername);
        edtUserNamePassword = findViewById(R.id.edtPassword);
        edtUserNameLogin = findViewById(R.id.edtUsernameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        BtnsignUp = findViewById(R.id.btnSignup);
        BtnLogin = findViewById(R.id.btnLogin);

        BtnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appuser = new ParseUser();
                appuser.setUsername(edtUsernameSignUp.getText().toString());
                appuser.setPassword(edtUserNamePassword.getText().toString());
                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUp.this, appuser.get("username") + "sign up successful", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){
                            FancyToast.makeText(SignUp.this, user.get("username") + "is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }

                    }
                });

            }
        });
    }
}
