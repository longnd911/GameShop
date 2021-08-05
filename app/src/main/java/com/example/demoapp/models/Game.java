package com.example.demoapp.models;

import java.io.Serializable;

public class Game implements Serializable{
    int _id;

    int resId;
    String title, content;
    float price;
    String thumbnail;

    public Game(int resId) {
        this.resId = resId;
    }

    public Game(int resId, String title, String content, float price) {
        this.resId = resId;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Game(String title, String content, float price, String thumbnail) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public Game(String title, String content, float price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
