package com.app.sogal.MoreInfoForAction;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.sogal.R;

import java.util.ArrayList;
import java.util.Calendar;

public class TimerInfo extends AppCompatActivity {

    ArrayList<String> additionalValue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}