package org.techtown.elderlyperson;

public class User {
    private String name;
    private int age;
    private  String img_ipaddress;

    public User(String name, int age,String img_ipaddress) {
        this.name = name;
        this.age = age;
        this.img_ipaddress=img_ipaddress;
    }

    public String getImg_ipaddress() {
        return img_ipaddress;
    }

    public void setImg_ipaddress(String img_ipaddress) {
        this.img_ipaddress = img_ipaddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
