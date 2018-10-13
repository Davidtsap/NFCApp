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
    Button btnUserInfo;
    Button btnPassInfo;
    Button btnEmailInfo;
    Button btnPhoneInfo;
    Button btnImageInfo;

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

        btnUserInfo = (Button)findViewById(R.id.btnUserInfo);
        btnUserInfo.setOnClickListener(this);
        btnPassInfo = (Button)findViewById(R.id.btnPassInfo);
        btnPassInfo.setOnClickListener(this);
        btnEmailInfo = (Button)findViewById(R.id.btnEmailInfo);
        btnEmailInfo.setOnClickListener(this);
        btnPhoneInfo = (Button)findViewById(R.id.btnPhoneInfo);
        btnPhoneInfo.setOnClickListener(this);
        btnImageInfo = (Button)findViewById(R.id.btnImageInfo);
        btnImageInfo.setOnClickListener(this);



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
            inputUser = new User();
            inputUser.setName(edtUserName.getText().toString());
            String pass = (edtPassword.getText().toString());
            inputUser.setEmail(edtEmail.getText().toString());
            inputUser.setPhone(edtPhone.getText().toString());
            Gson gson = new Gson();
            gson.toJson(inputUser);
            try {
                String token = server.addNewUser(inputUser,pass);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userToken", token);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "could not register user ", Toast.LENGTH_LONG).show();


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
        if(v == btnUserInfo)
        {
            Toast.makeText(this, "Username must contain at least 3 characters", Toast.LENGTH_LONG).show();

        }
        if(v == btnPassInfo)
        {
            Toast.makeText(this, "Password must contain at least 8 characters", Toast.LENGTH_LONG).show();

        }
        if(v == btnEmailInfo)
        {
            Toast.makeText(this, "You must enter a valid email with @ that is not already registered to the app", Toast.LENGTH_LONG).show();
        }
        if(v == btnPhoneInfo)
        {
            Toast.makeText(this, "Phone must be formatted as: xxx - xxxxxxx", Toast.LENGTH_LONG).show();
        }
        if(v == btnImageInfo)
        {
            Toast.makeText(this, "Image must be PNG / JPEG", Toast.LENGTH_LONG).show();
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
