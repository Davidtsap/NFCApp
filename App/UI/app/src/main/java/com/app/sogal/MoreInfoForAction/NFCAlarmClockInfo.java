package com.app.sogal.MoreInfoForAction;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.sogal.R;

import java.util.ArrayList;
import java.util.Calendar;

public class NFCAlarmClockInfo extends Activity {

    static final int TIME_DIALOG_ID = 1111;
    Button btnClickTime;
    TextView tvTime;
    int hr =0;
    int min =0;

    ArrayList<String> additionalValue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_timer);

        tvTime = (TextView) findViewById(R.id.tvTime);
        final Calendar c = Calendar.getInstance();
        hr = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        updateTime(hr, min);
        addButtonClickListener();

        Button ok = (Button) findViewById(R.id.btnOk);
        Button cancel = (Button) findViewById(R.id.btnCancel3);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (additionalValue != null && additionalValue.size() == 0) {
                    additionalValue.add(Integer.toString(hr));
                    additionalValue.add(Integer.toString(min));
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("additionalValue", additionalValue);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                }
                finish();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addButtonClickListener() {
        btnClickTime= (Button) findViewById(R.id.btnClock);
        btnClickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hr, min, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });
    }


    private void updateTime(int hours, int mins) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
        String aTime = new StringBuilder().append(hours).append(':').append(minutes).append(" ").append(timeSet).toString();
        tvTime.setText(aTime);
    }
}
