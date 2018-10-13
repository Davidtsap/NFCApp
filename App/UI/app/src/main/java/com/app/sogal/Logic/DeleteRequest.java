package com.app.sogal.Logic;

import android.os.AsyncTask;

import com.app.sogal.Data.ServerAnswer;

import org.apache.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class DeleteRequest extends AsyncTask<String, Integer, ServerAnswer> {
    private String targetURL = "https://smartchip.herokuapp.com/api/";

    @Override
    protected ServerAnswer doInBackground(String... strings) {
        try {
            URL obj = new URL(targetURL + strings[0]);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            try {
                con.setRequestMethod("DELETE");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            con.setRequestProperty("User-Agent", HttpHeaders.USER_AGENT);
            if (strings[1] != null) {
                con.setRequestProperty("x-auth-token", strings[1]);
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
                ServerAnswer answer = new ServerAnswer();
                answer.setResponseCode(HttpURLConnection.HTTP_OK);
                answer.setMessage(response.toString());
                System.out.println(response.toString());
                return answer;
                // print result

            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}