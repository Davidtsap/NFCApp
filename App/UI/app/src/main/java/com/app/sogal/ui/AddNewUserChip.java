package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.app.sogal.R;


public class AddNewUserChip extends AppCompatActivity implements View.OnClickListener {
    Button btnScanNewChip;
    Button btnSetChipName;
    Button btnSetChipOp;
    TextView tvUserName;
    ImageView imvUserPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user_chip);

        tvUserName = (TextView)findViewById(R.id.tvUserName6);

        imvUserPic = (ImageView)findViewById(R.id.imvUserPic6);

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
            startActivity(new Intent(getApplicationContext(), SetChipNameActivity.class));
        }
        else if (v == btnSetChipOp)
        {
            startActivity(new Intent(getApplicationContext(), ConfiguringChipOperations.class));
        }
    }
}
