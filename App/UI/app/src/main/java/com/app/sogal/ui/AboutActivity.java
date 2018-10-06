package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.sogal.R;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnHamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnHamburger = (Button) findViewById(R.id.btnHamburger);
        btnHamburger.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnHamburger)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
