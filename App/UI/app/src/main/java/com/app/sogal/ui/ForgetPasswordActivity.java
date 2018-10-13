package com.app.sogal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSend;
    EditText edEmail;
    ServletApi server = new ServletApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        edEmail = (EditText) findViewById(R.id.edEmail);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSend)
        {
            if(edEmail.getText().toString() != null && !edEmail.getText().toString().isEmpty())
            {
                boolean success =server.resetAppPass(edEmail.getText().toString());
                if(success)
                    Toast.makeText(this,"password send to email", Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(this,"filed to send email,check you email", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
