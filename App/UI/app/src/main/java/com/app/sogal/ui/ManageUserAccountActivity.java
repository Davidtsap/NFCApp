package com.app.sogal.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sogal.Data.User;
import com.app.sogal.R;

public class ManageUserAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE = 1;

    Button btnHome;
    TextView tvUserName;
    ImageView imvUserPic;
    EditText edtUserName2;
    EditText edtPassword2;
    EditText edtEmail2;
    EditText edtPhone2;
    Button btnSaveChanges;
    Button btnEdit;
    User user;
    Button btnLoadPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_account);

        btnHome = (Button) findViewById(R.id.btnHome4);
        btnHome.setOnClickListener(this);

        btnEdit = (Button) findViewById((R.id.btnEdit));
        btnEdit.setOnClickListener(this);

        tvUserName = (TextView) findViewById(R.id.tvUserName3);

        imvUserPic = (ImageView) findViewById(R.id.imvUserPic2);

        btnSaveChanges = (Button) findViewById(R.id.btnSaveChanges);
        btnSaveChanges.setOnClickListener(this);
        btnLoadPic = (Button) findViewById(R.id.btnLoadPic2);
        btnLoadPic.setOnClickListener(this);
        edtUserName2 = (EditText) findViewById(R.id.edtUserName2);
        edtPassword2 = (EditText) findViewById(R.id.edtPassword2);
        edtEmail2 = (EditText) findViewById(R.id.edtEmail2);
        edtPhone2 = (EditText) findViewById(R.id.edtPhone2);
        user = MainActivity.user;

        edtUserName2.setText(user.getName());
        edtEmail2.setText(user.getEmail());
        edtPhone2.setText(user.getPhone());
        //edtPassword2.setText(user.getPassword());

    }

    @Override
    public void onClick(View v) {
        if (v == btnSaveChanges) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if (v == btnHome) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if (v == btnEdit) {
            startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
        }
        if (v == btnLoadPic) {
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
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap =BitmapFactory.decodeFile(picturePath);
            bitmap = Bitmap.createScaledBitmap(bitmap, 53, 39, false);
            imvUserPic.setImageBitmap(bitmap);

        }
    }
}
