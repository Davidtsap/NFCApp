package com.app.sogal.Logic;

import com.app.sogal.Data.ServerAnswer;
import com.app.sogal.Data.User;
import com.app.sogal.ui.MainActivity;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.app.sogal.Data.Chip;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServletApi {
    GetRequest GetRequest;
    PostRequest postRequst;
    PutRequest putRequest;
    DeleteRequest deleteRequest;

//    public String getNewNfcChipNumber() {
//        String nfcNumber =null;
//        try {
//            nfcNumber = getRequst.execute("getNFCNumber").get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        finally {
//            return nfcNumber;
//        }
//    }

    public String addNewUser(User newUser , String password) throws Exception{
        postRequst = new PostRequest();
        ServerAnswer answer;
        Gson gson = new Gson();
        String token = null;
        try {
            JsonObject innerObject = gson.toJsonTree(newUser).getAsJsonObject();
            innerObject.addProperty("password" , password);
            answer = postRequst.execute("users" ,innerObject.toString()).get();

            if(answer!=null){
                if(answer.getResponseCode() == 200) {
                    token = answer.getToken();
                }
                else if(answer.getResponseCode() == 400){
                    throw new Exception(answer.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return token;
    }

    public String userLogin(String email , String password) throws Exception{
        postRequst = new PostRequest();
        String id = null;
        ServerAnswer answer;
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("email", email);
        innerObject.addProperty("password", password);
        answer = postRequst.execute("auth",innerObject.toString()).get();
        if(answer!=null){
            if(answer.getResponseCode() == 200){
                id= answer.getMessage();
            }
            else if(answer.getResponseCode() == 400){
                throw new Exception(answer.getMessage());
            }
        }
        return id;
    }

    public Chip addUserNewChip(Chip chip) throws Exception{
        postRequst = new PostRequest();
        ServerAnswer answer;
        Gson gson = new Gson();
        Chip newChip = null;
        try {
            String chipString = gson.toJson(chip);
            answer = postRequst.execute("chips" ,chipString , MainActivity.user.getToken()).get();
            if(answer!=null){
                if(answer.getResponseCode() == 200){
                    newChip = gson.fromJson(answer.getMessage(),Chip.class);
                }
                else if(answer.getResponseCode() == 400){
                    throw new Exception(answer.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return newChip;
    }

    public String deleteUserChip(Chip chip , String token) {
        String returnMsg;
        ServerAnswer jsonChip = null;
        deleteRequest= new DeleteRequest();
        Gson gson =new Gson();
        try {
            jsonChip = deleteRequest.execute("chips/" + chip.getSerialNumber() ,token ).get();
            chip = gson.fromJson(jsonChip.getMessage(),Chip.class);
            returnMsg = "success";
        } catch (InterruptedException e) {
            returnMsg = jsonChip.getMessage();
        } catch (ExecutionException e) {
            returnMsg = jsonChip.getMessage();
        }
        return returnMsg;

    }

    public Chip updateUserChip(Chip chip) throws Exception {

        putRequest = new PutRequest();
        ServerAnswer answer;
        Gson gson = new Gson();
        Chip newChip = null;
        try {
            JsonObject innerObject = new JsonObject();
            innerObject.addProperty("name", chip.getChipName());
            innerObject.addProperty("action", chip.getAction());
            JsonArray list = new JsonArray();
            for(String value : chip.getAdditionalValues()) {
                list.add(value);
            }
            innerObject.add("options", list);
            String chipString = gson.toJson(innerObject);
            answer = putRequest.execute("chips/" + chip.getSerialNumber() ,chipString , MainActivity.user.getToken()).get();
            if(answer!=null){
                if(answer.getResponseCode() == 200){
                    newChip = gson.fromJson(answer.getMessage(),Chip.class);
                }
                else if(answer.getResponseCode() == 400){
                    throw new Exception(answer.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return newChip;
    }

    public Chip getActionByID(String ID) {
        String jsonChip = null;
        GetRequest= new GetRequest();
        Gson gson =new Gson();
        Chip chip =null;
        try {
            jsonChip = GetRequest.execute("chips/" + ID).get();
            chip = gson.fromJson(jsonChip,Chip.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return chip;
    }

    public List<Chip> getListOfChips(String token){
        GetRequest= new GetRequest();
        String jsonChip = null;
        Gson gson =new Gson();
        List<Chip> chip =null;
        try {
            jsonChip = GetRequest.execute("chips",token).get(); // +ID
            chip = gson.fromJson(jsonChip,new TypeToken<List<Chip>>(){}.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return chip;
    }

    public User getUserDetails(String token){
        GetRequest= new GetRequest();
        Gson gson =new Gson();
        String userDetails;
        User user =null;
        try {
            userDetails = GetRequest.execute("users/me" ,token).get(); // +ID
            user = gson.fromJson(userDetails,User.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User updateUserDetails(User ID){

        return null;
    }


    public void sendContactUsEmail(String subject, String body) throws Exception {
        postRequst = new PostRequest();
        ServerAnswer answer;
        Gson gson = new Gson();
        String token = null;
        try {
            JsonObject innerObject = new JsonObject();
            innerObject.addProperty("subject",subject);
            innerObject.addProperty("body" , body);
            answer = postRequst.execute("email" ,innerObject.toString()).get();
            if(answer!=null){
                if(answer.getResponseCode() == 200) {
                    token = answer.getToken();
                }
                else if(answer.getResponseCode() == 400){
                    throw new Exception(answer.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return token;
    }
}