package com.app.sogal.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.app.sogal.R;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ManageUserChips extends AppCompatActivity  implements View.OnClickListener {
    Button btnAddNewChip;
    TextView tvUserName;
    ImageView imvUserPic;
    RadioGroup radioGroup;

    String radioText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_chips);

        btnAddNewChip = (Button) findViewById(R.id.btnAddNewChip);
        btnAddNewChip.setOnClickListener(this);

        tvUserName = (TextView) findViewById(R.id.tvUserName);

        imvUserPic = (ImageView) findViewById(R.id.imvUserPic);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddNewChip) {
            showScanOptions();

        }
    }

    private void showScanOptions() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.scan_option_radio_group);
        List<String> stringList=new ArrayList<>();  // here is list
        String[] foo_array = this.getResources().getStringArray(R.array.scanOptions);
        for(int i=0;i<foo_array.length;i++) {
            stringList.add(foo_array[i]);
        }
        radioGroup = (RadioGroup) dialog.findViewById(R.id.scan_option_radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rb.setId(i+1);

            radioGroup.addView(rb);
        }
        Button confirmButton  = (Button) dialog.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);

                radioText = radioButton.getText().toString();
                startActivity(new Intent(getApplicationContext(), AddNewUserChip.class));
            }
        });
        dialog.show();
    }
}
