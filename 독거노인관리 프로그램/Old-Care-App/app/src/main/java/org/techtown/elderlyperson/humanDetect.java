package org.techtown.elderlyperson;

public class humanDetect {
    private String outDate;
    private String comebackDate;
    private String outCheck;
    private String id;

    public humanDetect( String comebackDate, String outCheck) {

        this.comebackDate = comebackDate;
        this.outCheck = outCheck;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getComebackDate() {
        return comebackDate;
    }

    public void setComebackDate(String comebackDate) {
        this.comebackDate = comebackDate;
    }

    public String getOutCheck() {
        return outCheck;
    }

    public void setOutCheck(String outCheck) {
        this.outCheck = outCheck;
    }


}
