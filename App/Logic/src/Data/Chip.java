package Data;

public class Chip {

    String serialNumber;
    String action;
    String chipName;
    public void setChipName(String chipName)
    {
        this.chipName = chipName;
    }
    public String getChipName(){
        return chipName;
    }
    public void setSerialNumber(String serialNumber){
        this.serialNumber =serialNumber;
    }
    public void setAction(String action){
        this.action= action;
    }
    public String getSerialNumber(){
        return this.serialNumber;
    }
    public String getAction(){
        return this.action;
    }
}
