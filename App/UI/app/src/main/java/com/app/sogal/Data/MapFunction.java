package com.app.sogal.Data;

import android.app.AlarmManager;

import com.app.sogal.MoreInfoForAction.*;
import com.app.sogal.OnlyAppUserAction.CallToPhone;
import com.app.sogal.OnlyAppUserAction.GlobalChip;
import com.app.sogal.OnlyAppUserAction.NFCAlarmClock;
import com.app.sogal.OnlyAppUserAction.SendTextMessage;
import com.app.sogal.OnlyAppUserAction.Timer;
import com.app.sogal.OnlyAppUserAction.URLForwording;

import java.util.HashMap;
import java.util.Map;

public class MapFunction {

    public static final Map<String, Class> MapInfo = createInfoMap();
    public static final Map<String, Class<? extends GlobalChip>> MapGlobalString = createMap();
    public static final Map<String, Class> MapInternalAction = createInternalMap();

    private static Map<String,Class> createInternalMap() {
        Map<String,Class> myMap = new HashMap ();
        myMap.put("Call Someone", CallToPhone.class);
        myMap.put("Send Message", SendTextMessage.class);
        myMap.put("Open Web Page", URLForwording.class);
        myMap.put("Set Alarm Clock", NFCAlarmClock.class);
        myMap.put("Set Timer", Timer.class);
        return  myMap;
    }

    private static Map<String, Class> createInfoMap()
    {
        Map<String,Class> myMap = new HashMap<String,Class>();
        myMap.put("Call Someone", CallToPhoneInfo.class);
        myMap.put("Send Message", SendTextMessageInfo.class);
        myMap.put("Open Web Page", URLInfo.class);
        myMap.put("Set Alarm Clock", NFCAlarmClockInfo.class);
        myMap.put("Set Timer", TimerInfo.class);

        return myMap;
    }

    private static Map<String,Class<? extends GlobalChip>> createMap(){
        Map<String,Class<? extends GlobalChip>> myMap = new HashMap ();
        myMap.put("Call Someone", CallToPhone.class);
        myMap.put("Send Message", SendTextMessage.class);
        myMap.put("Open Web Page", URLForwording.class);


        return myMap;
    }
}
