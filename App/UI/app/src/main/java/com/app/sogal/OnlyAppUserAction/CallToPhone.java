package com.app.sogal.OnlyAppUserAction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.app.sogal.Data.Chip;
import com.google.gson.Gson;

import java.util.List;


/**
 * Created by David on 17/04/2018.
 */

public class CallToPhone extends Activity implements  GlobalChip{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String chipAsString = getIntent().getStringExtra("Chip");
        Gson gson = new Gson();
        Chip chip = gson.fromJson(chipAsString, Chip.class);
        String phone = getPhoneFromChip(chip);
        MakeCall(phone);
    }

    private String getPhoneFromChip(Chip chip) {
        List<String> addvalue = chip.getAdditionalValues();
        String phoneNumber =null;
        if(addvalue != null && !addvalue.isEmpty()){
           return phoneNumber = addvalue.get(0).replaceAll("[^0-9]", "");
        }
        return null;
    }

    public void MakeCall(String phone){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           Toast.makeText(this, "need premissions", Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(callIntent);
    }

    public  String getGlobalStringToScan(Chip chip){
        if(chip.getAdditionalValues().get(0) != null){
            String phone = chip.getAdditionalValues().get(0);
            return phone;
        }
        return null;
    }
}
