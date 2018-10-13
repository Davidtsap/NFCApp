package com.app.sogal.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sogal.Data.Chip;
import com.app.sogal.Data.MapFunction;
import com.app.sogal.Data.User;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.app.sogal.ui.MainActivity.user;

public class EditChip extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    TextView tvChipName;
    Spinner spinner;
    Button btnSave;
    Button btnCancel;
    String function;
    List <String> additionalValue = new ArrayList<>();

    Chip chip;
    ServletApi server = new ServletApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chip);
        String chipString =getIntent().getStringExtra("chip");
        Gson gson = new Gson();
        chip = gson.fromJson(chipString,Chip.class);

        tvChipName = (TextView) findViewById(R.id.chipName2);
        spinner = (Spinner) findViewById(R.id.spinner2);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        tvChipName.setText(chip.getChipName());
        additionalValue = chip.getAdditionalValues();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, getListOfFunctions());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private List<String> getListOfFunctions() {
        ArrayList<String> list =  new ArrayList<String>();
        for(String str : MapFunction.MapInfo.keySet()){
            if(str.equalsIgnoreCase(chip.getAction())){
                list.add(str);
            }
        }
        for(String str : MapFunction.MapInfo.keySet()){
            if(!str.equalsIgnoreCase(chip.getAction())){
                list.add(str);
            }
        }
        return list;
    }

    @Override
    public void onClick(View view) {
        if (view == btnSave)
        {
            if (tvChipName.getText().toString().isEmpty() || function.equalsIgnoreCase("none")) {
                Toast.makeText(this, "You have to fill all the info", Toast.LENGTH_LONG).show();
            } else {
                startActivityFroWriting();
            }
        }
        if (view == btnCancel)
        {
            finish();
        }
    }

    private void startActivityFroWriting(){
        Intent intent = new Intent(getBaseContext(), ManageUserChips.class);
        Chip chip = prepareChip();
        try {
            chip = server.updateUserChip(chip);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Chip prepareChip() {


        chip.setAction(function);
        chip.setChipName(tvChipName.getText().toString());
        chip.setAdditionalValues(additionalValue);
        return chip;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner)adapterView;
        if(spin.getId() == R.id.spinner2) {
            function = spinner.getSelectedItem().toString();
            showMoreInfo(function);
        }
    }

    private void showMoreInfo(String function) {
        if(!function.equalsIgnoreCase("none")) {
            Intent intent = new Intent(getApplicationContext(), MapFunction.MapInfo.get(function));
            intent.putStringArrayListExtra("additionalValue",(ArrayList)additionalValue);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (0 == reqCode) {
            if (resultCode == Activity.RESULT_OK) {
                System.out.println("in on ActivityResult");
                Bundle bundle = data.getExtras();
                additionalValue = (ArrayList<String>) bundle.getSerializable("additionalValue");
            }
        }
    }
}
