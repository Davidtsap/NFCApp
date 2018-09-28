package com.app.sogal.Data;

import java.util.List;

public class Chip {
    String serialNumber;
    String action;
    String chipName;
    List<String> additionalValues;

    public Chip() {
    }

    public List<String> getAdditionalValues() {
        return additionalValues;
    }

    public void setAdditionalValues(List<String> additionalValues) {
        this.additionalValues = additionalValues;
    }
    public void setChipName(String chipName) {
        this.chipName = chipName;
    }

    public String getChipName() {
        return this.chipName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public String getAction() {
        return this.action;
    }
}
