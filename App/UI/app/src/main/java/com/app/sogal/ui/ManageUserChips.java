package com.app.sogal.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.app.sogal.Data.Chip;
import com.app.sogal.Data.User;
import com.app.sogal.Logic.ChipAdapter;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ManageUserChips extends AppCompatActivity  implements View.OnClickListener {
    Button btnAddNewChip;
    ImageView imvUserPic;
    TextView tvUserName;
    RadioGroup radioGroup;
    ListView chipListView;
    String radioText;
    Button btnHome;

    ServletApi server = new ServletApi();
    ChipAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user_chips);

        btnHome = (Button) findViewById(R.id.btnHome5);
        btnHome.setOnClickListener(this);

        btnAddNewChip = (Button) findViewById(R.id.btnAddNewChip);
        btnAddNewChip.setOnClickListener(this);

        tvUserName = (TextView) findViewById(R.id.userName4);
        tvUserName.setText("Hello " + MainActivity.user.getName());

        imvUserPic = (ImageView) findViewById(R.id.imvUserPic);
        //imvUserPic.setImageDrawable(MainActivity.user.getImage());

        chipListView = (ListView)findViewById(R.id.ChipList);
        List<Chip> chipList = getListOfChip(MainActivity.user);
        adapter = new ChipAdapter(this,chipList);
        chipListView.setAdapter(adapter);
    }

    private List<Chip> getListOfChip(User user) {
        return server.getListOfChips(user.getToken());
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddNewChip) {
            showScanOptions();

        }
        if (v == btnHome)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                Intent intent = new Intent(getApplicationContext(), AddNewUserChip.class);
                intent.putExtra("chipType", radioText);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
