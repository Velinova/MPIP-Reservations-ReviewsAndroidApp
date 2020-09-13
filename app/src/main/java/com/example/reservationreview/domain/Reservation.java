package com.example.reservationreview.domain;

import java.util.Date;

public class Reservation {
    private String name;
    private int guests;
    private String date;

    public Reservation(String name, int guests, String date){
        this.name = name;
        this.guests = guests;
        this.date = date;
    }
    public Reservation(){

    }
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
