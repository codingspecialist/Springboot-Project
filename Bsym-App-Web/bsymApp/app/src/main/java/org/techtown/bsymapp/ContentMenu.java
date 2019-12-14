package org.techtown.bsymapp;


public class ContentMenu {
    private int image;
    private String title;
    private String Link;
    boolean likeCheck;
    private int id;

    public ContentMenu(String title,String Link,boolean likeCheck,int id) {
        this.title = title;
        this.Link =Link;
        this.likeCheck  = likeCheck;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLikeCheck() {
        return likeCheck;
    }

    public void setLikeCheck(boolean likeCheck) {
        this.likeCheck = likeCheck;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}