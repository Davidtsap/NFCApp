package com.app.sogal.MoreInfoForAction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.sogal.R;

import java.util.List;

public class CallToPhoneInfo implements MoreInfo{


    LayoutInflater inflater;
    public List<String> MoreInfo(Activity context){
        inflater = LayoutInflater.from(context);
        showDialog(context);

        return null;
    }

    private void showDialog(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("choose phone number");
        View view = inflater.inflate(R.layout.call_phone_dialog,null);
        EditText phoneNumber = (EditText) view.findViewById(R.id.phoneTextDialog);
        Button contact = (Button) view.findViewById(R.id.ButtonContactDialog);
        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                activity.startActivityForResult(intent, 0);
            }
        });
        TextView moreInfo = (TextView)view.findViewById(R.id.morePhoneInfo);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
