package com.app.sogal.Logic;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class PostRequst extends AsyncTask<String, Integer, String> {
    private String targetURL = "https://smartchip.herokuapp.com/api/";

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL obj = new URL(targetURL + strings[0]);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type","application/json");
            if(strings.length == 3){
                con.setRequestProperty("x-auth-token",strings[2]);
            }
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(strings[1].getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
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
                System.out.println("POST request not worked");
                return null;
            }
        } catch (Exception e){
            return null;
        }
        finally {
        }
    }
}
