package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_save;
    Button btnCancel;
    ImageView imvUserPic;
    TextView tvUserName;
    EditText oldPass;
    EditText newPass;
    ServletApi server =  new ServletApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newPass= (EditText) findViewById(R.id.edNewPass);
        oldPass = (EditText) findViewById(R.id.edCurrentPass);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        imvUserPic = (ImageView) findViewById(R.id.imvUserPic);
        //imvUserPic.setImageDrawable(MainActivity.user.getImage());
        tvUserName = (TextView) findViewById(R.id.userName5);
        tvUserName.setText("Hello " + MainActivity.user.getName());
    }

    @Override
    public void onClick(View v) {
        if(v == btn_save)
        {

            try {
                server.changePass(newPass.getText().toString(),oldPass.getText().toString(),MainActivity.user.getToken());
            } catch (Exception e) {
                //Toast.makeText(this,)
            }
            startActivity(new Intent(getApplicationContext(), ManageUserAccountActivity.class));

        }
        if(v == btnCancel)
        {
            startActivity(new Intent(getApplicationContext(), ManageUserAccountActivity.class));

        }
    }
}
