package com.app.sogal.MoreInfoForAction;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sogal.R;

import java.util.ArrayList;
import java.util.List;

public class SendTextMessageInfo  extends Activity {

    EditText phoneNumber;
    EditText message;

    ArrayList<String> additionalValue = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_send_text_message);
        phoneNumber = (EditText) findViewById(R.id.textMessagePhoneNumber);
        Button contact = (Button) findViewById(R.id.textMessageContactButton);
        message = (EditText) findViewById(R.id.textMessageEditText);
        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        Button ok = (Button) findViewById(R.id.textMessageOk);
        Button cancel = (Button) findViewById(R.id.textMessageCancel);
        ok.setOnClickListener(new View.OnClickListener() {
            String text ="";
            @Override
            public void onClick(View view) {
                if (message.getText().toString() != null && !message.getText().toString().isEmpty()) {
                    if (additionalValue != null && additionalValue.size() != 0) {
                        additionalValue.add(message.getText().toString());
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("additionalValue", additionalValue);
                        intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK, intent);
                    }
                    finish();
                } else {

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(getIntent().hasExtra("additionalValue")){
            List<String> temp1list =(List <String>) getIntent().getSerializableExtra("additionalValue");
            phoneNumber.setText(temp1list.get(0));
            message.setText(temp1list.get(1));
        }
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (0 == reqCode) {
            if (resultCode == Activity.RESULT_OK) {
                System.out.println("in on ActivityResult");
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, null, null, null, null);
                if (c.moveToFirst()) {
                    String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                    if (hasPhone.equalsIgnoreCase("1")) {
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + " = " + id, null, null);
                        phones.moveToFirst();
                        String cNumber = phones.getString(phones.getColumnIndex("data1"));
                        String name=phones.getString(phones.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                        //here you can find out all the thing.
                        System.out.println("NAME:"+name);
                        phoneNumber.setText(cNumber);
                        additionalValue.add(cNumber);
                    }
                }
            }
        }

    }
}
