package com.app.sogal.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sogal.Data.Chip;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;


import com.app.sogal.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;



public class AddNewUserChip extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    Button btnFinish;
    TextView tvUserName;
    ImageView imvUserPic;
    EditText ChipName;

    Spinner spinner;
    EditText phoneNumber;
    Button contact;
    TextView moreInfo;

    //Data members
    String function;
    List <String> additionalValue = new ArrayList<>();
    ServletApi servlet = new ServletApi();
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user_chip);

        tvUserName = (TextView)findViewById(R.id.tvUserName6);

        imvUserPic = (ImageView)findViewById(R.id.imvUserPic6);

        ChipName = (EditText) findViewById(R.id.ChipName);

        spinner = (Spinner) findViewById(R.id.spinner);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        contact = (Button) findViewById(R.id.contact);
        contact.setOnClickListener(this);
        moreInfo = (TextView)findViewById(R.id.moreInfo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.FunctionAvalible, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnFinish = (Button)findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // need to check how we send to the next activity the s.number of this specific chip
        if(v == btnFinish) {
            if (ChipName.getText().toString().isEmpty()) {
                Toast.makeText(this, "You have to fill all the info", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(getBaseContext(), WriteNfcTag.class);
                String nfcNumber = servlet.getNewNfcChipNumber();
                Toast.makeText(this, nfcNumber, Toast.LENGTH_LONG).show();
                Chip chip = new Chip();
                chip.setAction(function);
                chip.setChipName(ChipName.getText().toString());
                chip.setSerialNumber(nfcNumber);
                additionalValue.add(m_Text);
                chip.setAdditionalValues(additionalValue);
                Gson gson = new Gson();
                String chipJson = gson.toJson(chip);
                intent.putExtra("Chip", chipJson);
                startActivity(intent);

            }
        }
        if (v == contact) {
            Intent intent = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner)adapterView;
        if(spin.getId() == R.id.spinner) {
            function = spinner.getSelectedItem().toString();
            showMoreInfo(function);
        }
    }

    private void showMoreInfo(String function) {
        if(function.equalsIgnoreCase("CallToPhone")){
            moreInfo.setVisibility(View.VISIBLE);
            contact.setVisibility(View.VISIBLE);
            phoneNumber.setVisibility(View.VISIBLE);
        }
        if(function.equalsIgnoreCase("SendTextMessage")){
            moreInfo.setVisibility(View.VISIBLE);
            contact.setVisibility(View.VISIBLE);
            phoneNumber.setVisibility(View.VISIBLE);
            getTxetFromUser();
        }
    }

    private void getTxetFromUser() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter text message");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_FLAG_MULTI_LINE );
        input.setLines(8);
        input.setMinLines(6);
        input.setGravity(Gravity.LEFT |Gravity.TOP);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
