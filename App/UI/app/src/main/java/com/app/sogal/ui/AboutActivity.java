package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.sogal.R;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnHome)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
