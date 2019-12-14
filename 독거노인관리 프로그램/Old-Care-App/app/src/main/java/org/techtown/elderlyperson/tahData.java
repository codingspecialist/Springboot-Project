package org.techtown.elderlyperson;

public class tahData {
    private String createDate;
    private String temp;
    private String humi;
    private String id;

    public tahData(String createDate, String temp, String humi) {
        this.createDate = createDate;
        this.temp = temp;
        this.humi = humi;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
