package com.example.myapp.Models;

import java.io.Serializable;

public class Anouncements implements Serializable {

    String  image_uri  , How ,when , what , anouncement_id  ,date_time;

    public Anouncements() {

    }

    public Anouncements(String image_uri, String what , String how, String when, String anouncement_id , String date_time) {
        this.image_uri = image_uri;
        How = how;
        this.when = when;
        this.what = what;
        this.anouncement_id = anouncement_id;
        this.date_time = date_time;
    }



    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }


    public String getHow() {
        return How;
    }

    public void setHow(String how) {
        How = how;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getAnouncement_id() {
        return anouncement_id;
    }

    public void setAnouncement_id(String anouncement_id) {
        this.anouncement_id = anouncement_id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
