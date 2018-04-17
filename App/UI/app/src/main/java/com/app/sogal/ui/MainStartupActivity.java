package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import java.time.Instant;


class MainStartupActivity extends AppCompatActivity implements View.OnClickListener {
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
    }

    public void buttonExit_onClick(View view)
    {

    }
    @Override
    public void onClick(View view) {

        if (view==btnLogin)
        {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        else if(view==btnRegister)
        {

        }
    }
}
