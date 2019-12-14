package org.techtown.bsymapp;

public class as{
    public final String title;
    public final String imglink;
    public final String link;
    public  final  int id;
    boolean likeCheck;

    public as(String title,String imglink,String link,int id){
        this.title=title;
        this.imglink=imglink;
        this.link=link;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public String getImglink() {
        return imglink;
    }

    public String getLink() {
        return link;
    }

    public int getId() {
        return id;
    }

    public boolean isLikeCheck() {
        return likeCheck;
    }

    public void setLikeCheck(boolean likeCheck) {
        this.likeCheck = likeCheck;
    }
}