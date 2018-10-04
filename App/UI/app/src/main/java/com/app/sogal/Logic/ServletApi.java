package com.app.sogal.Logic;

import com.app.sogal.Data.User;
import com.app.sogal.ui.MainActivity;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.app.sogal.Data.Chip;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class ServletApi {
    GetRequst getRequst = new GetRequst();
    PostRequst postRequst = new PostRequst();

    public String getNewNfcChipNumber() {
        String nfcNumber =null;
        try {
            nfcNumber = getRequst.execute("getNFCNumber").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            return nfcNumber;
        }
    }

    public boolean addNewUser(User newUser){

        return true;
    }

    public String userLogin(String email , String password){
        String id = null;
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("email", email);
        innerObject.addProperty("password", password);
        try{
         id = postRequst.execute("auth",innerObject.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Chip addUserNewChip(Chip chip) {
        Gson gson = new Gson();
        Chip newChip = null;
        try {
            String chipString = gson.toJson(chip);
            String strChip = postRequst.execute("chips" ,chipString , MainActivity.user.getToken()).get();
            newChip = gson.fromJson(strChip,Chip.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return newChip;
    }

    public boolean deleteUserChip(Chip chip) {

        return true;
    }

    public boolean updateUserChip(Chip chip) {

        return true;
    }

    public Chip getActionByID(String ID) {
        String jsonChip = null;
        Gson gson =new Gson();
        Chip chip =null;
        try {
            jsonChip = getRequst.execute("getAction&NFCNumber=" + ID).get();
            chip = gson.fromJson(jsonChip,Chip.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return chip;
    }

    public List<Chip> getListOfChips(String token){

        String jsonChip = null;
        Gson gson =new Gson();
        List<Chip> chip =null;
        try {
            jsonChip = getRequst.execute("chips",token).get(); // +ID
            chip = gson.fromJson(jsonChip,new TypeToken<List<Chip>>(){}.getType());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return chip;
    }

    public User getUserDetails(String token){
        Gson gson =new Gson();
        String userDetails;
        User user =null;
        try {
            userDetails = getRequst.execute("users/me" ,token).get(); // +ID
            user = gson.fromJson(userDetails,User.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUserDetails(String ID){

        return true;
    }

}