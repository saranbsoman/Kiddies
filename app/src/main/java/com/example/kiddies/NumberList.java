package com.example.kiddies;

//class for data model
public class NumberList {

    //declaring member variables to data
    private int imgId;
    private String num;

    public NumberList() {
    }

    //constructor to initialise values
    public NumberList(String num,int imgId) {
        this.imgId = imgId;
        this.num = num;
    }

    //getter for imgId
    public int getImgId() {
        return imgId;
    }

    //setter for imgId
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    //getter for num
    public String getNum() {
        return num;
    }

    //setter for num
    public void setNum(String num) {
        this.num = num;
    }
}
