package com.app.sogal.Logic;

import com.google.gson.Gson;

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

    public void UpdateNewChip(Chip chip) {
        Gson gson = new Gson();
        String chipString = gson.toJson(chip);
        postRequst.execute("updateNFCMethod" ,chipString);

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
}
