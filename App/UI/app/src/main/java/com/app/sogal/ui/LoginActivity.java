package com.app.sogal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmail;
    EditText edPassword;
    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (EditText)findViewById(R.id.edEmail);
        edPassword = (EditText)findViewById(R.id.edPassword);
        btnLogIn = (Button)findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnLogIn)
        {
            if (edEmail.getText().toString().length() > 0 && edPassword.getText().toString().length() > 0)
            {
                if (!edEmail.getText().toString().contains("@") || (!edEmail.getText().toString().contains(".")))
                {
                    Toast.makeText(this, "Unvalid email", Toast.LENGTH_LONG).show();
                }
                edEmail.setText("");
                edPassword.setText("");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            }
        }
    }
}
