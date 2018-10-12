package com.app.sogal.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.sogal.Data.User;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE = 1;
    Button btnSignUp;
    EditText edtUserName;
    EditText edtPassword;
    EditText edtEmail;
    EditText edtPhone;
    User inputUser;
    Button btnLoadPic;
    ImageView ImvUserPic;

    ServletApi server = new ServletApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone3);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setEnabled(false);
        btnSignUp.setOnClickListener(this);

        edtUserName.addTextChangedListener(watcher);
        edtPassword.addTextChangedListener(watcher);
        edtEmail.addTextChangedListener(watcher);
        edtPhone.addTextChangedListener(watcher);

        btnLoadPic = (Button) findViewById(R.id.btnLoadPic);
        btnLoadPic.setOnClickListener(this);

        ImvUserPic = (ImageView) findViewById(R.id.ImvUserPic);

    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (edtUserName.toString().trim().length() == 0 || edtPassword.toString().trim().length() == 0 ||
                    edtEmail.toString().trim().length() == 0 || edtPhone.toString().trim().length() == 0)
            {
                btnSignUp.setEnabled(false);
            } else {
                btnSignUp.setEnabled(true);
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v==btnSignUp)
        {
            inputUser.setName(edtUserName.getText().toString());
            String pass = (edtPassword.getText().toString());
            inputUser.setEmail(edtEmail.getText().toString());
            inputUser.setPhone(edtPhone.getText().toString());
            Gson gson = new Gson();
            gson.toJson(inputUser);
            try {
                server.addNewUser(inputUser,pass);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();


            }
            //http.executePost("name Of Fuction" ,Gson gsonObj (gson.toJson(inputUser)));

        }
        if(v == btnLoadPic)
        {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImvUserPic.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
}
}
