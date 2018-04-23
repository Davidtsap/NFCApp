package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SetChipNameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvUserName;
    ImageView imvUserPic;
    EditText edtChipName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_chip_name);

        tvUserName = (TextView)findViewById(R.id.tvUserName5);

        imvUserPic = (ImageView)findViewById(R.id.imvUserPic4);

        edtChipName = (EditText)findViewById(R.id.edtChipName);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnSave){
            startActivity(new Intent(getApplicationContext(), AddNewUserChip.class));
        }
    }
}
