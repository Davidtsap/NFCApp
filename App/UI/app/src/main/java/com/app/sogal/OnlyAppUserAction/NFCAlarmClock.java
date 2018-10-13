package com.app.sogal.OnlyAppUserAction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;

public class NFCAlarmClock extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int hour = 20;
        int minute = 30;
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        i.putExtra(AlarmClock.EXTRA_HOUR, hour);
        i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
       // i.putExtra(AlarmClock.EXTRA_MESSAGE, "Good Morning");
        startActivity(i);
        finish();
    }


}
