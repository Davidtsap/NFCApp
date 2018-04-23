package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignUp;
    EditText edtUserName;
    EditText edtPassword;
    EditText edtEmail;
    EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
    }

    @Override
    public void onClick(View v) {

        if (v==btnSignUp)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
