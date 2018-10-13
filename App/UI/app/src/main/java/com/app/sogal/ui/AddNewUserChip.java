package com.app.sogal.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sogal.Data.Chip;
import com.app.sogal.Data.MapFunction;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.OnlyAppUserAction.GlobalChip;
import com.app.sogal.R;


import java.util.ArrayList;
import java.util.List;

import static com.app.sogal.Data.MapFunction.MapGlobalString;


public class AddNewUserChip extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    Button btnFinish;
    TextView tvUserName;
    ImageView imvUserPic;
    EditText ChipName;
    Button btnHome;
    Spinner spinner;

    //Data members
    String function;
    String chipType;
    List <String> additionalValue = new ArrayList<>();
    ServletApi servlet = new ServletApi();
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user_chip);

        btnHome = (Button)findViewById(R.id.btnHome2);
        btnHome.setOnClickListener(this);
        imvUserPic = (ImageView)findViewById(R.id.imvUserPic6);
        //imvUserPic.setImageDrawable(MainActivity.user.getImage());
        tvUserName = (TextView) findViewById(R.id.userName6);
        tvUserName.setText("Hello "+ MainActivity.user.getName());

        ChipName = (EditText) findViewById(R.id.ChipName);
        if(getIntent().hasExtra("chipType")){
            chipType = getIntent().getStringExtra("chipType");
            System.out.println(chipType);
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, getListOfFunctions());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnFinish = (Button)findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

    }

    private List<String> getListOfFunctions() {
        ArrayList<String> list =  new ArrayList<String>();
        list.add("none");
        if(chipType.equalsIgnoreCase("Everyone"))
            list.addAll(MapFunction.MapGlobalString.keySet());
        else
            list.addAll(MapFunction.MapInternalAction.keySet());
        return list;
    }

    @Override
    public void onClick(View v) {
        // need to check how we send to the next activity the s.number of this specific chip
        if(v == btnFinish) {
            if (ChipName.getText().toString().isEmpty() || function.equalsIgnoreCase("none")) {
                Toast.makeText(this, "You have to fill all the info", Toast.LENGTH_LONG).show();
            } else {
                startActivityFroWriting();
            }
        }
        if(v == btnHome){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    private void startActivityFroWriting(){
        Intent intent = new Intent(getBaseContext(), WriteNfcTag.class);
        Chip chip = prepareChip();
        try {
            chip = servlet.addUserNewChip(chip);
            String scan;
            if(chipType.equalsIgnoreCase("Everyone") ) {
                scan = getGlobalStringToScan(chip);
                intent.putExtra("Function", chip.getAction());
                intent.putExtra("Global", true);
            }
            else{
                intent.putExtra("Global", false);
                scan = chip.getSerialNumber();
            }
            intent.putExtra("ScanOnChip", scan);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(this, "something went wrong, please try again", Toast.LENGTH_LONG).show();
        }
    }

    private String getGlobalStringToScan(Chip chip){
        Class classChip = MapGlobalString.get(chip.getAction());
        try {
            GlobalChip globalChip= (GlobalChip)classChip.newInstance();
            return  globalChip.getGlobalStringToScan(chip);

        }catch (Exception ex){

        }

        return null;
    }

    private Chip prepareChip() {

        Chip chip = new Chip();
        chip.setAction(function);
        chip.setChipName(ChipName.getText().toString());
        chip.setAdditionalValues(additionalValue);
        if(chipType.equalsIgnoreCase("Everyone"))
            chip.setIsGlobal(true);
        else
            chip.setIsGlobal(false);
        return chip;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner)adapterView;
        if(spin.getId() == R.id.spinner) {
            function = spinner.getSelectedItem().toString();
            showMoreInfo(function);
        }
    }

    private void showMoreInfo(String function) {
        if(!function.equalsIgnoreCase("none")) {
            Intent intent = new Intent(getApplicationContext(), MapFunction.MapInfo.get(function));
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
                additionalValue = (ArrayList<String>) bundle.getStringArrayList("additionalValue");
            }
        }
    }


}
