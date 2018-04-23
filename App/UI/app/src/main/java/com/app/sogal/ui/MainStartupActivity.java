package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

import com.app.sogal.R;
    Button btnLogin;
    Button btnRegister;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_startup);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRgister);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btnLogin)
        {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        else if(v == btnRegister)
        {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }
        else  if(v == btnExit)
        {

        }
    }
}
