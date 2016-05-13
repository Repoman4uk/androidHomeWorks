package com.itis.androidlab.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature implements Parcelable{
    private Double day;
    private Double night;
    @JsonProperty("eve")
    private Double evening;
    @JsonProperty("morn")
    private Double morning;
    public Temperature(){}
    public Double getDay(){return day;}
    public void setDay(Double day){this.day=day;}
    public Double getNight(){return night;}
    public void setNight(Double night){this.night=night;}
    public Double getEvening(){return evening;}
    public void setEvening(Double evening){this.evening=evening;}
    public Double getMorning(){return morning;}
    public void setMorning(Double morning){this.morning=morning;}
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeDouble(morning);
        parcel.writeDouble(day);
        parcel.writeDouble(evening);
        parcel.writeDouble(night);
    }
    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        // распаковываем объект из Parcel
        public Temperature createFromParcel(Parcel in) {

            return new Temperature(in);
        }

        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };
    private Temperature(Parcel parcel) {
        morning=parcel.readDouble();
        day=parcel.readDouble();
        evening=parcel.readDouble();
        night=parcel.readDouble();
    }
    /*@JsonProperty("temp")
    private Double temperature;
    private Integer pressure;
    @JsonProperty("temp_min")
    private Double temperatureMin;
    @JsonProperty("temp_max")
    private Double temperatureMax;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }*/
    @Override
    public String toString() {
        return "Temperature{" +
                "day=" + day +
                ", night=" + night +
                ", evening=" + evening +
                ", morning=" + morning +
                '}';
    }
}
