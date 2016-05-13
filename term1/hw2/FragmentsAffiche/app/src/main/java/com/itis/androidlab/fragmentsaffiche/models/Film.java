package com.itis.androidlab.fragmentsaffiche.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film implements Parcelable {
    private String title;
    private String director;
    private String description;
    @JsonProperty("premier_date")
    private String date;
    private int id;


    public Film() {
    }

    protected Film(Parcel in) {
        id=in.readInt();
        title = in.readString();
        director = in.readString();
        description = in.readString();
        date = in.readString();

    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
    public int getId(){return id;};
    public void setId(int id){this.id=id;};
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(director);
        dest.writeString(description);
        dest.writeString(date);


    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FilmArray {
        public ArrayList<Film> items;

        public ArrayList<Film> getItems() {
            return items;
        }

        public void setItems(ArrayList<Film> items) {
            this.items = items;
        }
    }
}
