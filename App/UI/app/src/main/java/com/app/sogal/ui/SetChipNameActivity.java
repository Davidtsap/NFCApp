package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sogal.Logic.GetRequst;
import com.app.sogal.Logic.PostRequst;
import com.app.sogal.R;
import com.google.gson.Gson;

import Data.Chip;
import Http.HttpRequest;

public class SetChipNameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvUserName;
    ImageView imvUserPic;
    EditText edtChipName;
    Button btnSave;
    HttpRequest http = new HttpRequest();
    PostRequst postRequst = new PostRequst();
    Chip inChip;


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
        if((v==btnSave) && (edtChipName.getText().toString().length()>0)){
            inChip.setChipName(edtChipName.getText().toString());
            Gson gson = new Gson();
            gson.toJson(inChip);
            //http.executePost("name Of Fuction" ,Gson gsonObj (gson.toJson(inChip)));
            //postRequst.execute("updateNFCMethod",gson.toJson(inChip).toString());
            postRequst.execute("updateNFCMethod","{\n" +
                    "\t\"NFCNumber\":\"NFCApp-1#\",\n" +
                    "\t\"Action\":\"CallToPhone\"\n" +
                    "}");
            startActivity(new Intent(getApplicationContext(), AddNewUserChip.class));
        }
        
    }
}
