package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.app.sogal.R;


public class AddNewUserChip extends AppCompatActivity implements View.OnClickListener {
    Button btnScanNewChip;
    Button btnSetChipName;
    Button btnSetChipOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user_chip);

        btnScanNewChip = (Button)findViewById(R.id.btnScanNewChip);
        btnScanNewChip.setOnClickListener(this);
        btnSetChipName = (Button)findViewById(R.id.btnSetChipName);
        btnSetChipName.setOnClickListener(this);
        btnSetChipOp = (Button)findViewById(R.id.btnSetChipOp);
        btnSetChipOp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnScanNewChip)
        {
            startActivity(new Intent(getApplicationContext(), WriteNfcTag.class));
        }
        else if(v == btnSetChipName)
        {

        }
        else if (v == btnSetChipOp){

        }
    }
}
