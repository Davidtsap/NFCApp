package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.sogal.R;
import com.google.gson.Gson;

import Data.Chip;
import Http.HttpRequest;

public class PhoneModeActivity extends AppCompatActivity implements View.OnClickListener {
    HttpRequest http = new HttpRequest();
    Chip inChip;
    Button btnSetAutomaticSms;
    Button btnSetSpeedDialing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_mode);

        btnSetSpeedDialing = (Button)findViewById(R.id.btnSetSpeedDialing);
        btnSetSpeedDialing.setOnClickListener(this);

        btnSetAutomaticSms = (Button)findViewById(R.id.btnSetAutomaticSms);
        btnSetAutomaticSms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSetSpeedDialing)
        {
            // need to change this! no need to open a new chip. this should be the same chip from the last activity
            inChip.setAction("SpeedDialing");
            Gson gson = new Gson();
            gson.toJson(inChip);
            //http.executePost("name Of Function" ,Gson gsonObj (gson.toJson(inChip)));
            startActivity(new Intent(getApplicationContext(), AddNewUserChip.class));

        }
        else if (v == btnSetAutomaticSms)
        {
            inChip.setAction("AutomaticSms");
            Gson gson = new Gson();
            gson.toJson(inChip);
            //http.executePost("name Of Function" ,Gson gsonObj (gson.toJson(inChip)));
        }
    }
}
