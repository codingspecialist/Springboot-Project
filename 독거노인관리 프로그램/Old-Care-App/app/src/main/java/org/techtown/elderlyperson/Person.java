package org.techtown.elderlyperson;


import java.util.List;

public class Person {
    private List<tahData> tahData;
    private User user;
    private List<humanDetect> humanDetect;
    private gasDetect gasDetect;

    public Person(List<tahData> tahData, User user, List<humanDetect> humanDetect,gasDetect gasDetect) {
        this.tahData = tahData;
        this.user = user;
        this.humanDetect = humanDetect;
        this.gasDetect=gasDetect;

    }

    public org.techtown.elderlyperson.gasDetect getGasDetect() {
        return gasDetect;
    }

    public void setGasDetect(org.techtown.elderlyperson.gasDetect gasDetect) {
        this.gasDetect = gasDetect;
    }

    public List<tahData> getTahData() {
        return tahData;
    }

    public void setTahData(List<tahData> tahData) {
        this.tahData = tahData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<humanDetect> getHumanDetect() {
        return humanDetect;
    }

    public void setHumanDetect(List<humanDetect> humanDetect) {
        this.humanDetect = humanDetect;
    }


}
