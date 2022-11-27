package com.example.myapp.Models;

public class post_feed {
    String comments , post_text ,datetime ,image_uri;

    public post_feed() {
    }

    public post_feed(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
