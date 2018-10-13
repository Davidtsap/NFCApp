package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sogal.R;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnHome;
    ImageView imvUserPic;
    TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        imvUserPic = (ImageView)findViewById(R.id.imvUserPic8);
        //imvUserPic.setImageDrawable(MainActivity.user.getImage());
        tvUserName = (TextView) findViewById(R.id.userName3);
        tvUserName.setText("Hello " + MainActivity.user.getName());


    }

    @Override
    public void onClick(View v) {
        if (v == btnHome)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
