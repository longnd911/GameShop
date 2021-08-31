package com.example.app;

import java.io.Serializable;

public class GameInfo implements Serializable {
    String name;
    String price;
    String content;
    String thumbnail;

    public GameInfo() {
    }

    public GameInfo(String name, String price, String content, String thumbnail) {
        this.name = name;
        this.price = price;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
