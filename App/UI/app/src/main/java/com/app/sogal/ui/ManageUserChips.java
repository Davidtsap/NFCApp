package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageUserChips extends AppCompatActivity  implements View.OnClickListener {
    Button btnAddNewChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_chips);
        btnAddNewChip = (Button)findViewById(R.id.btnAddNewChip);
        btnAddNewChip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnAddNewChip){
            startActivity(new Intent(getApplicationContext(), NfcScanner.class));
        }
    }
}
