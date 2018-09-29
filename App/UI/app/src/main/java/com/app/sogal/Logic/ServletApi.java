package com.app.sogal.Logic;

import com.app.sogal.Data.User;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.app.sogal.Data.Chip;

public class ServletApi {
    GetRequst getRequst = new
            GetRequst();
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

    public boolean userLogin(User user){

        return true;
    }

    public boolean addUserNewChip(Chip chip) {
        Gson gson = new Gson();
        String chipString = gson.toJson(chip);
        postRequst.execute("updateNFCMethod" ,chipString);

        return true;
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

    public List<Chip> getListOfChips(String ID){

        return null;
    }

    public User getUserDetails(String ID){

        return null;
    }

    public boolean updateUserDetails(String ID){

        return true;
    }

}