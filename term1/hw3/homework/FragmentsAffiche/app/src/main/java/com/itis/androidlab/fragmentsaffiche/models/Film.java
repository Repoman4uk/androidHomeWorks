package com.itis.androidlab.fragmentsaffiche.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film implements Parcelable {
    private Long id;
    private String title;
    private String director;
    private String description;
    @JsonProperty("premier_date")
    private String date;
    private ArrayList<Genre> genres;

    public Film() {
    }

    protected Film(Parcel in) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        dest.writeString(title);
        dest.writeString(director);
        dest.writeString(description);
        dest.writeString(date);
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FilmArray implements Parcelable {
        public ArrayList<Film> items;

        public FilmArray() {
            items = new ArrayList<>();
        }

        public FilmArray(ArrayList<Film> items) {
            this.items = items;
        }

        protected FilmArray(Parcel in) {
            items = in.createTypedArrayList(Film.CREATOR);
        }

        public void add(Film film) {
            items.add(film);
        }

        public static final Creator<FilmArray> CREATOR = new Creator<FilmArray>() {
            @Override
            public FilmArray createFromParcel(Parcel in) {
                return new FilmArray(in);
            }

            @Override
            public FilmArray[] newArray(int size) {
                return new FilmArray[size];
            }
        };

        public ArrayList<Film> getItems() {
            return items;
        }

        public void setItems(ArrayList<Film> items) {
            this.items = items;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(items);
        }
    }
}
