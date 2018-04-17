package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnManageUserChips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageUserChips = (Button)findViewById(R.id.btnManageUserChips);
        btnManageUserChips.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnManageUserChips){
            startActivity(new Intent(getApplicationContext(), ManageUserChips.class));
        }
    }
}
