package com.app.sogal.ui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
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

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnHome;
    Button btnSend;
    EditText edSubject;
    TextInputEditText tinMessage;
    ImageView imvUserPic;
    TextView tvUserName;

    ServletApi server = new ServletApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        btnHome = (Button) findViewById(R.id.btnHome3);
        btnHome.setOnClickListener(this);

        imvUserPic = (ImageView) findViewById(R.id.imvUserPic3);
        //imvUserPic.setImageDrawable(MainActivity.user.getImage());
        tvUserName = (TextView) findViewById(R.id.userName2);
        tvUserName.setText("Hello " + MainActivity.user.getName());

        btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        edSubject = (EditText) findViewById(R.id.edSubject);

//        tinMessage = (TextInputEditText) findViewById(R.id.tinMessage);
    }

    @Override
    public void onClick(View v) {
        if (v == btnHome)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if(v == btnSend) {
            try {
                server.sendContactUsEmail(edSubject.getText().toString(), tinMessage.getText().toString());
                Toast.makeText(getApplicationContext(), "Your message was send", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            catch (Exception ex){
                Toast.makeText(getApplicationContext(), "Error with send message, please try again..", Toast.LENGTH_LONG).show();
            }
        }
    }
}
