package com.itis.androidlab.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullWeatherInfo implements Parcelable{


    @JsonProperty("list")
    private List<Weather> weather;

    /*
    @JsonProperty("coord")
    private Coordinates coordinates;

    private String base;

    @JsonProperty("main")
    private Temperature temperature;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }*/
    public FullWeatherInfo(){}
    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeTypedList(weather);
    }
    public static final Parcelable.Creator<FullWeatherInfo> CREATOR = new Parcelable.Creator<FullWeatherInfo>() {
        // распаковываем объект из Parcel
        public FullWeatherInfo createFromParcel(Parcel in) {

            return new FullWeatherInfo(in);
        }

        public FullWeatherInfo[] newArray(int size) {
            return new FullWeatherInfo[size];
        }
    };
    private FullWeatherInfo(Parcel parcel) {
        if (weather==null) weather=new ArrayList<Weather>();
        parcel.readTypedList(weather,Weather.CREATOR);
    }


    /*
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }*/

    @Override
    public String toString() {
        String s="FullWeatherInfo{["+weather.get(0).toString()+"]";
        for (int i=1;i<weather.size();i++) s+=", ["+weather.get(i).toString()+"]";
        return s +
                '}';
    }
}
