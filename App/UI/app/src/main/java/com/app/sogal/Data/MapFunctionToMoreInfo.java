package com.app.sogal.Data;

import com.app.sogal.MoreInfoForAction.*;

import java.util.HashMap;
import java.util.Map;

public class MapFunctionToMoreInfo {

    public static final Map<String, Class> myMap = createMap();
    private static Map<String, Class> createMap()
    {

        Map<String,Class> myMap = new HashMap<String,Class>();
        myMap.put("CallToPhone", CallToPhoneInfo.class);
        myMap.put("SendTextMessage", SendTextMessageInfo.class);
        myMap.put("URL", URLInfo.class);
        return myMap;
    }
}
