package com.example.myapp.Models;

public class Events {
    String Tittle , Date_time , Descriptions, image_uri , event_Id;

    public Events() {
    }

    public Events(String image_uri, String tittle, String descriptions, String date_time, String event_Id) {
        Tittle = tittle;
        Date_time = date_time;
        Descriptions = descriptions;
        this.image_uri = image_uri;
        this.event_Id = event_Id;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getDate_time() {
        return Date_time;
    }

    public void setDate_time(String date_time) {
        Date_time = date_time;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getEvent_Id() {
        return event_Id;
    }

    public void setEvent_Id(String event_Id) {
        this.event_Id = event_Id;
    }
}
