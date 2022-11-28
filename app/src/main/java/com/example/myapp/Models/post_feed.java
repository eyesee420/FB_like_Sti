package com.example.myapp.Models;

public class post_feed {
    String  image_uri,  post_text ,datetime  , post_id;

    public post_feed() {
    }

    public post_feed(String image_uri, String post_text, String datetime, String post_id) {
        this.image_uri = image_uri;
        this.post_text = post_text;
        this.datetime = datetime;
        this.post_id = post_id;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
