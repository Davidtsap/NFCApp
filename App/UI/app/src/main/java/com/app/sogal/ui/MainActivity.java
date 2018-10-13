package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sogal.Data.User;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnManageUserChips;
    Button btnShop;
    Button btnManageUserAccount;
    Button btnAbout;
    Button btnContactUs;
    Button btnLogOut;
    TextView userName;
    ImageView imUserPic;
    ServletApi servlet = new ServletApi();
    public static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageUserChips = (Button) findViewById(R.id.btnManageUserChips);
        btnManageUserChips.setOnClickListener(this);

        btnShop = (Button) findViewById(R.id.btnShop);
        btnShop.setOnClickListener(this);

        btnManageUserAccount = (Button) findViewById(R.id.btnManageUserAccount);
        btnManageUserAccount.setOnClickListener(this);

        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(this);

        btnContactUs = (Button) findViewById(R.id.btnContactUs);
        btnContactUs.setOnClickListener(this);

        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);

        userName = (TextView) findViewById(R.id.userName);
        imUserPic = (ImageView) findViewById(R.id.imvUserPic7);
        //imUserPic.setImageDrawable(MainActivity.user.getImage());

        String userToken;
        if(user == null || getIntent().hasExtra("userToken" )){
            userToken = getIntent().getStringExtra("userToken");
            getUserDetails(userToken);
        }
        else if (user != null)
        {
            userName.setText("Hello " +user.getName());
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnManageUserChips) {
            startActivity(new Intent(getApplicationContext(), ManageUserChips.class));
        } else if (v == btnShop) {
            startActivity(new Intent(getApplicationContext(), ShopActivity.class));
        } else if (v == btnManageUserAccount) {
            startActivity(new Intent(getApplicationContext(), ManageUserAccountActivity.class));
        } else if (v == btnAbout) {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
        } else if (v == btnContactUs) {
            startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
        } else if (v == btnLogOut) {
            startActivity(new Intent(getApplicationContext(), MainStartupActivity.class));
        }

    }
    
    private void getUserDetails (String token){
        User userDetails = servlet.getUserDetails(token);
        user = userDetails;
        user.setToken(token);
        userName.setText("Hello " +user.getName());
    }
}