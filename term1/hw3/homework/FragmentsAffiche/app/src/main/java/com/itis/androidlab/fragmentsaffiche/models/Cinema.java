package com.itis.androidlab.fragmentsaffiche.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

public class Cinema {
    private String name;
    private String address;
    private ArrayList<Long> films;
    public Cinema(){

    }
    public String getName(){return name;};
    public void setName(String name){ this.name=name;};
    public String getAddress(){return address;};
    public void setAddress(){this.address=address;};
    public ArrayList<Long> getFilms(){return films;};
    public void setFilms(ArrayList<Long> films){this.films=films;};
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CinemaArray{
        public ArrayList<Cinema> items;
        public ArrayList<Cinema> getItems() {
            return items;
        }

        public void setItems(ArrayList<Cinema> items) {
            this.items = items;
        }
    }

}
