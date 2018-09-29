package com.app.sogal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.sogal.Data.User;
import com.app.sogal.R;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignUp;
    EditText edtUserName;
    EditText edtPassword;
    EditText edtEmail;
    EditText edtPhone;
    User inputUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
       // edtPhone = (EditText)findViewById(R.id.edtPhone);
    }

    @Override
    public void onClick(View v) {

        if (v==btnSignUp)
        {
            //inputUser.setUserName(edtUserName.getText().toString());
            //inputUser.setPassword(edtPassword.getText().toString());
            //inputUser.seteMail(edtEmail.getText().toString());
            //inputUser.setPhone(edtPhone.getText().toString());
            Gson gson = new Gson();
            gson.toJson(inputUser);
            //http.executePost("name Of Fuction" ,Gson gsonObj (gson.toJson(inputUser)));
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
