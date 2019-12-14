package org.techtown.bsymapp;


public class SelectMenu {
    private int image;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public SelectMenu(int image,int num) {
        this.image = image;
        this.num=num;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}