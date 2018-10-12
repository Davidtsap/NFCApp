package com.app.sogal.OnlyAppUserAction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.sogal.Data.Chip;
import com.google.gson.Gson;

public class URLForwording extends AppCompatActivity implements  GlobalChip{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String chipAsString = getIntent().getStringExtra("Chip");
        Gson gson = new Gson();
        Chip chip = gson.fromJson(chipAsString, Chip.class);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chip.getAdditionalValues().get(0)));
        startActivity(browserIntent);
    }

    @Override
    public String getGlobalStringToScan(Chip chip) {
        String url  = chip.getAdditionalValues().get(0);
        if (!url.startsWith("http://") && !url.startsWith("https://"))
        {url = "http://" + url;}
        return url;
    }
}
