package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.sogal.R;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_save;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_save)
        {
            startActivity(new Intent(getApplicationContext(), ManageUserAccountActivity.class));

        }
        if(v == btnCancel)
        {
            startActivity(new Intent(getApplicationContext(), ManageUserAccountActivity.class));

        }
    }
}
