package org.techtown.elderlyperson;

public class gasDetect {
    private String createDate;
    private String gasCheck;

    public gasDetect(String createDate, String gasCheck) {
        this.createDate = createDate;
        this.gasCheck = gasCheck;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGasCheck() {
        return gasCheck;
    }

    public void setGasCheck(String gasCheck) {
        this.gasCheck = gasCheck;
    }
}
