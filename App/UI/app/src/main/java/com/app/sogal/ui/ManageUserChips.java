package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.app.sogal.R;
import android.widget.ImageView;
import android.widget.TextView;

public class ManageUserChips extends AppCompatActivity  implements View.OnClickListener {
    Button btnAddNewChip;
    TextView tvUserName;
    ImageView imvUserPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_chips);

        btnAddNewChip = (Button) findViewById(R.id.btnAddNewChip);
        btnAddNewChip.setOnClickListener(this);

        tvUserName = (TextView) findViewById(R.id.tvUserName);

        imvUserPic = (ImageView) findViewById(R.id.imvUserPic);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddNewChip) {
            startActivity(new Intent(getApplicationContext(), AddNewUserChip.class));
        }
    }
}
