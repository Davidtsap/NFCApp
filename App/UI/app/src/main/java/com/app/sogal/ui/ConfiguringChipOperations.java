package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.sogal.R;

public  class ConfiguringChipOperations extends AppCompatActivity implements View.OnClickListener {

    Button btnPhoneMode;
    Button btnSettingUpTasks;
    Button btnAccessibility;
    Button btnBusinesslike;
    TextView tvChipName;
    ImageView imvUserPic;
    TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuring_chip_operations);

        tvChipName = (TextView)findViewById(R.id.tvChipName);
        tvUserName = (TextView)findViewById(R.id.tvUserName4);

        imvUserPic = (ImageView)findViewById(R.id.imvUserPic5);

        btnPhoneMode = (Button)findViewById(R.id.btnPhoneMode);
        btnPhoneMode.setOnClickListener(this);

        btnSettingUpTasks = (Button)findViewById(R.id.btnSettingUpTasks);
        btnSettingUpTasks.setOnClickListener(this);

        btnAccessibility = (Button)findViewById(R.id.btnAccessibility);
        btnAccessibility.setOnClickListener(this);

        btnBusinesslike = (Button)findViewById(R.id.btnBusinesslike);
        btnBusinesslike.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnPhoneMode){
            // need to send to the next activity the s.number of the chip for update in the dataBase
            startActivity(new Intent(getApplicationContext(), PhoneModeActivity.class));
        }
        else if(v==btnSettingUpTasks){

        }
        else if(v==btnAccessibility){

        }
        else if(v==btnBusinesslike){

        }
    }
}
