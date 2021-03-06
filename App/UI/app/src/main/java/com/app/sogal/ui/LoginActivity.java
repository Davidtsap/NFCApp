package com.app.sogal.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.sogal.Data.User;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    String userEmail,userPassword;
    EditText edEmail;
    EditText edPassword;
    Button btnLogIn;
    Button btnForgotPass;
    CheckBox checkBox;
    Button btnEmailInfo;
    Button btnPassInfo;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    Boolean saveLogin;

    ServletApi servlet = new ServletApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (EditText)findViewById(R.id.edEmail);
        edPassword = (EditText)findViewById(R.id.edPassword);
        edEmail.addTextChangedListener(watcher);
        edPassword.addTextChangedListener(watcher);
        btnLogIn = (Button)findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnLogIn.setEnabled(false);
        btnForgotPass = (Button) findViewById(R.id.btnForgotPass);
        btnForgotPass.setOnClickListener(this);
        btnEmailInfo = (Button) findViewById(R.id.btnEmailInfo);
        btnEmailInfo.setOnClickListener(this);
        btnPassInfo = (Button) findViewById(R.id.btnPassInfo);
        btnPassInfo.setOnClickListener(this);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        loginPrefsEditor.apply();


        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
            edEmail.setText(loginPreferences.getString("username", ""));
            edPassword.setText(loginPreferences.getString("password", ""));
            checkBox.setChecked(true);
        }
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (edEmail.toString().trim().length() == 0 || edPassword.toString().trim().length() == 0) {
                btnLogIn.setEnabled(false);
            } else {
                btnLogIn.setEnabled(true);
            }
        }
    };
    @Override
    public void onClick(View view) {
        if(view == btnLogIn)
        {
            if (edEmail.getText().toString().length() > 0 && edPassword.getText().toString().length() > 0)
            {
                if (!edEmail.getText().toString().contains("@") || (!edEmail.getText().toString().contains(".")))
                {
                    Toast.makeText(this, "Unvalid email", Toast.LENGTH_LONG).show();
                }
                else {
                    String email = edEmail.getText().toString();
                    String password = edPassword.getText().toString();
                    try {
                        String tokenUser = servlet.userLogin(email, password);
                        if (tokenUser != null) {
                            if (checkBox.isChecked()) {
                                loginPrefsEditor.putBoolean("saveLogin", true);
                                loginPrefsEditor.putString("username", edEmail.getText().toString());
                                loginPrefsEditor.putString("password", edPassword.getText().toString());
                                loginPrefsEditor.commit();
                            } else {
                                loginPrefsEditor.clear();
                                loginPrefsEditor.commit();
                            }
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("userToken", tokenUser);
                            startActivity(intent);
                        }
                    }
                    catch(Exception ex){
                        Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
            else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            }
        }
        if(view == btnForgotPass)
        {
            startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
        }
        if(view == btnEmailInfo)
        {
            Toast.makeText(this, "You must enter a valid email with @ that registered to the app", Toast.LENGTH_LONG).show();
        }
        if (view == btnPassInfo)
        {
            Toast.makeText(this, "Password must contain at least 8 characters", Toast.LENGTH_LONG).show();
        }
    }
}
