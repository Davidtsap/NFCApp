package com.app.sogal.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sogal.Data.Chip;
import com.app.sogal.Logic.ServletApi;
import com.app.sogal.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WriteNfcTag extends AppCompatActivity {


    public static final String ERROR_DETECTED = "No NFC tag detected!";
    public static final String WRITE_SUCCESS = "Text written to the NFC tag successfully!";
    public static final String WRITE_ERROR = "Error during writing, is the NFC tag close enough to your device?";
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    boolean writeMode;
    Tag myTag;
    Context context;
    //Chip chip;
    String scanOnChip;
    boolean Global;
    String function;

    ServletApi server = new ServletApi();

    Map<String,String> myMap = new HashMap<String,String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMap();
        setContentView(R.layout.activity_write_nfc_tag);
        context = this;
        scanOnChip = getIntent().getStringExtra("ScanOnChip");
        Global = getIntent().getBooleanExtra("Global",false);
        if(Global){
            function = getIntent().getStringExtra("Function");
        }

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
        }
       // readFromIntent(getIntent());

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writeTagFilters = new IntentFilter[] { tagDetected };
    }

    private void initMap() {
        myMap.put("CallToPhone", "tel:");
        myMap.put("SendTextMessage", "sms:");
        myMap.put("URLForwarding","");
    }

    /******************************************************************************
     **********************************Write to NFC Tag****************************
     ******************************************************************************/
    private void write(String text, Tag tag) throws IOException, FormatException {

        NdefRecord record;
        if(Global){
            record =createGlobalRecord(text);
        }else {
            record = createRecord(text);
        }
        NdefRecord[] records = {record};
        NdefMessage message = new NdefMessage(records);
        // Get an instance of Ndef for the tag.
        Ndef ndef = Ndef.get(tag);
        // Enable I/O
        ndef.connect();
        // Write the message
        ndef.writeNdefMessage(message);
        // Close the connection
        ndef.close();
    }
    private NdefRecord createRecord(String text) throws UnsupportedEncodingException {
        String lang       = "en";
        byte[] textBytes  = text.getBytes();
        byte[] langBytes  = lang.getBytes("US-ASCII");
        int    langLength = langBytes.length;
        int    textLength = textBytes.length;
        byte[] payload    = new byte[1 + langLength + textLength];

        // set status byte (see NDEF spec for actual bits)
        payload[0] = (byte) langLength;
        // copy langbytes and textbytes into payload
        System.arraycopy(langBytes, 0, payload, 1,langLength);
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);
        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT,  new byte[0], payload);

        return recordNFC;
    }

    private NdefRecord createGlobalRecord(String text) throws UnsupportedEncodingException {

        String func  = myMap.get(function);
        String uri = func + text;
        NdefRecord recordNFC = NdefRecord.createUri(uri);
        return recordNFC;
    }



    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        //message = http.executeGet("getNFCNumber",null);

        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
        try {
            if(myTag ==null) {
                Toast.makeText(context, ERROR_DETECTED, Toast.LENGTH_LONG).show();
            } else {
                write(scanOnChip, myTag);
                Toast.makeText(context, WRITE_SUCCESS, Toast.LENGTH_LONG).show();
              //  server.addUserNewChip(chip);
            }
        } catch (IOException e) {
            Toast.makeText(context, WRITE_ERROR, Toast.LENGTH_LONG ).show();
            e.printStackTrace();
        } catch (FormatException e) {
            Toast.makeText(context, WRITE_ERROR, Toast.LENGTH_LONG ).show();
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public void onPause(){
        super.onPause();
        WriteModeOff();
    }

    @Override
    public void onResume(){
        super.onResume();
        WriteModeOn();
    }

    /******************************************************************************
     **********************************Enable Write********************************
     ******************************************************************************/
    private void WriteModeOn(){
        writeMode = true;
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }
    /******************************************************************************
     **********************************Disable Write*******************************
     ******************************************************************************/
    private void WriteModeOff(){
        writeMode = false;
        nfcAdapter.disableForegroundDispatch(this);
    }

}


//final String[] URI_PREFIX = new String[] {
//    /* 0x00 */ "",
//        /* 0x01 */ "http://www.",
//        /* 0x02 */ "https://www.",
//        /* 0x03 */ "http://",
//        /* 0x04 */ "https://",
//        /* 0x05 */ "tel:",
//        /* 0x06 */ "mailto:",
//        /* 0x07 */ "ftp://anonymous:anonymous@",
//        /* 0x08 */ "ftp://ftp.",
//        /* 0x09 */ "ftps://",
//        /* 0x0A */ "sftp://",
//        /* 0x0B */ "smb://",
//        /* 0x0C */ "nfs://",
//        /* 0x0D */ "ftp://",
//        /* 0x0E */ "dav://",
//        /* 0x0F */ "news:",
//        /* 0x10 */ "telnet://",
//        /* 0x11 */ "imap:",
//        /* 0x12 */ "rtsp://",
//        /* 0x13 */ "urn:",
//        /* 0x14 */ "pop:",
//        /* 0x15 */ "sip:",
//        /* 0x16 */ "sips:",
//        /* 0x17 */ "tftp:",
//        /* 0x18 */ "btspp://",
//        /* 0x19 */ "btl2cap://",
//        /* 0x1A */ "btgoep://",
//        /* 0x1B */ "tcpobex://",
//        /* 0x1C */ "irdaobex://",
//        /* 0x1D */ "file://",
//        /* 0x1E */ "urn:epc:id:",
//        /* 0x1F */ "urn:epc:tag:",
//        /* 0x20 */ "urn:epc:pat:",
//        /* 0x21 */ "urn:epc:raw:",
//        /* 0x22 */ "urn:epc:",
//        /* 0x23 */ "urn:nfc:"
//        };


