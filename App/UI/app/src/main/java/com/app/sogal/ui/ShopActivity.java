package com.app.sogal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.sogal.R;

public class ShopActivity extends AppCompatActivity {
    Button btnUserBag;
    TextView tvUserName;
    ImageView imvUserPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        btnUserBag = (Button)findViewById(R.id.btnUserBag);

        tvUserName = (TextView)findViewById(R.id.tvUserName2);

        imvUserPic = (ImageView)findViewById(R.id.imvUserPic2);
    }
}
