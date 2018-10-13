package com.app.sogal.Logic;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class GetRequest extends AsyncTask<String, Integer, String> {

    private String targetURL = "https://smartchip.herokuapp.com/api/";
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL obj = new URL(targetURL + strings[0]);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            try {
                con.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
           // con.setRequestProperty("User-Agent", USER_AGENT);
            if(strings.length == 2){
                con.setRequestProperty("x-auth-token",strings[1]);
            }
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
                return response.toString();
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
