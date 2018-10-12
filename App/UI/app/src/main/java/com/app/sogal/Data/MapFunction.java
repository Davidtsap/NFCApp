package com.app.sogal.Data;

import com.app.sogal.MoreInfoForAction.*;
import com.app.sogal.OnlyAppUserAction.CallToPhone;
import com.app.sogal.OnlyAppUserAction.GlobalChip;
import com.app.sogal.OnlyAppUserAction.SendTextMessage;
import com.app.sogal.OnlyAppUserAction.URLForwording;

import java.util.HashMap;
import java.util.Map;

public class MapFunction {

    public static final Map<String, Class> MapInfo = createInfoMap();
    public static final Map<String, Class<? extends GlobalChip>> MapGlobalString = createMap();
    public static final Map<String, Class> MapInternalAction = createInternalMap();

    private static Map<String,Class> createInternalMap() {
        Map<String,Class> myMap = new HashMap ();
        myMap.put("CallToPhone", CallToPhone.class);
        myMap.put("SendTextMessage", SendTextMessage.class);
        myMap.put("URLForwarding", URLForwording.class);
        return  myMap;
    }

    private static Map<String, Class> createInfoMap()
    {
        Map<String,Class> myMap = new HashMap<String,Class>();
        myMap.put("CallToPhone", CallToPhoneInfo.class);
        myMap.put("SendTextMessage", SendTextMessageInfo.class);
        myMap.put("URLForwarding", URLInfo.class);
        return myMap;
    }

    private static Map<String,Class<? extends GlobalChip>> createMap(){
        Map<String,Class<? extends GlobalChip>> myMap = new HashMap ();
        myMap.put("CallToPhone", CallToPhone.class);
        myMap.put("SendTextMessage", SendTextMessage.class);
        myMap.put("URLForwarding", URLForwording.class);
        return myMap;
    }
}
