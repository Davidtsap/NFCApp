package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sogal.Data.User;
import com.app.sogal.R;

public class ManageUserAccountActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUserName;
    ImageView imvUserPic;
    EditText edtUserName2;
    EditText edtPassword2;
    EditText edtEmail2;
    EditText edtPhone2;
    Button btnSaveChanges;
    EditText edtImage;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_account);

        tvUserName = (TextView)findViewById(R.id.tvUserName3);

        imvUserPic = (ImageView)findViewById(R.id.imvUserPic3);

        btnSaveChanges = (Button)findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(this);

        edtUserName2 = (EditText)findViewById(R.id.edtUserName2);
        edtPassword2 = (EditText)findViewById(R.id.edtPassword2);
        edtEmail2 = (EditText)findViewById(R.id.edtEmail2);
        edtPhone2 = (EditText)findViewById(R.id.edtPhone2);
        edtImage = (EditText)findViewById(R.id.edtImage);
        user = MainActivity.user;

        edtUserName2.setText(user.getName());
        edtEmail2.setText(user.getEmail());
        edtPhone2.setText(user.getPhone());
        //edtPassword2.setText(user.getPassword());

    }

    @Override
    public void onClick(View v) {
        if(v==btnSaveChanges){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
