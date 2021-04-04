package com.sabithpkcmnr.unsplashjson;

public class ModelList {

    int id, width, height;
    String author, imageUrl, downloadUrl;

    public ModelList() {
    }

    public ModelList(int id, int width, int height, String author, String imageUrl, String downloadUrl) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.author = author;
        this.imageUrl = imageUrl;
        this.downloadUrl = downloadUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
