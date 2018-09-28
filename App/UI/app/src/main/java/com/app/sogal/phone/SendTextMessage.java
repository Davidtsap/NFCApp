package com.app.sogal.phone;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.app.sogal.Data.Chip;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SendTextMessage extends Activity {

    private static final int SEND_SMS_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String chipAsString = getIntent().getStringExtra("Chip");
        Gson gson = new Gson();
        Chip chip = gson.fromJson(chipAsString, Chip.class);
        String phone = getPhoneFromChip(chip);
        String message = getMessegeFromChip(chip);

        permissionCheck();
        sendSms(phone,message);
        finish();
    }

    private String getMessegeFromChip(Chip chip) {
        List<String> addvalue = chip.getAdditionalValues();
        String phoneNumber =null;
        if(addvalue != null && !addvalue.isEmpty()){
            return phoneNumber = addvalue.get(1);
        }
        return null;
    }

    private String getPhoneFromChip(Chip chip) {
        List<String> addvalue = chip.getAdditionalValues();
        String phoneNumber =null;
        if(addvalue != null && !addvalue.isEmpty()){
            return phoneNumber = addvalue.get(0).replaceAll("[^0-9]", "");
        }
        return null;
    }
    private void permissionCheck(){
        if (!isSendSmsAllowed()) {
            requestSmsSendPermission();
            return;
        }
    }

    private boolean isSendSmsAllowed() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }

    private void sendSms(String phone , String message){
        SmsManager sms = SmsManager.getDefault();
       // ArrayList<String> parts = sms.divideMessage(message);
        sms.sendTextMessage(phone, null, message, null, null);
        Toast.makeText(this, "message send successfully", Toast.LENGTH_LONG).show();
    }

    //Requesting permission
    private void requestSmsSendPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.SEND_SMS },
                SEND_SMS_CODE);
    }
}
