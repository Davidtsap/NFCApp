package com.app.sogal.MoreInfoForAction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.sogal.R;

import java.util.ArrayList;
import java.util.List;

public class URLInfo  extends Activity {

    EditText URL;
    ArrayList<String> additionalValue = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_url);
        URL = (EditText) findViewById(R.id.URLtextView);
        Button ok = (Button) findViewById(R.id.URLOk);
        Button cancel = (Button) findViewById(R.id.URLCancel);
        ok.setOnClickListener(new View.OnClickListener() {
            String text ="";
            @Override
            public void onClick(View view) {
                if (URL.getText().toString() != null && !URL.getText().toString().isEmpty()) {
                    String url  = URL.getText().toString();
                    if (!url.startsWith("http://") && !url.startsWith("https://"))
                    {url = "http://" + url;}
                    additionalValue.add(url);
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("additionalValue", additionalValue);
                        intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(getIntent().hasExtra("additionalValue")){
            List<String> temp1list = (List <String>) getIntent().getSerializableExtra("additionalValue");
            URL.setText(temp1list.get(0));
        }
    }

}
