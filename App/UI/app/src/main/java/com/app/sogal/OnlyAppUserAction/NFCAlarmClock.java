package com.app.sogal.OnlyAppUserAction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;

import com.app.sogal.Data.Chip;
import com.google.gson.Gson;

public class NFCAlarmClock extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String chipAsString = getIntent().getStringExtra("Chip");
        Gson gson = new Gson();
        Chip chip = gson.fromJson(chipAsString, Chip.class);

        int hour = Integer.parseInt(chip.getAdditionalValues().get(0));
        int minute = Integer.parseInt(chip.getAdditionalValues().get(1));
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        i.putExtra(AlarmClock.EXTRA_HOUR, hour);
        i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
       // i.putExtra(AlarmClock.EXTRA_MESSAGE, "Good Morning");
        startActivity(i);
        finish();
    }


}
