package com.app.sogal.Data;

import java.util.List;

public class Chip {
    String _id;
    String action;
    String name;
    boolean isGlobal;
    List<String> options;

    public Chip() {
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(boolean global) {
        isGlobal = global;
    }

    public List<String> getAdditionalValues() {
        return options;
    }

    public void setAdditionalValues(List<String> additionalValues) {
        this.options = additionalValues;
    }
    public void setChipName(String chipName) {
        this.name = chipName;
    }

    public String getChipName() {
        return this.name;
    }

    public void setSerialNumber(String serialNumber) {
        this._id = serialNumber;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSerialNumber() {
        return this._id;
    }

    public String getAction() {
        return this.action;
    }
}
