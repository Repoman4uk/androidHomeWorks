package com.itis.androidlab.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Parcelable{
    private Temperature temp;
    @JsonProperty("dt")
    private long dt;
    private Calendar date;
    private String dateString;
    public void setTemp(Temperature temp){this.temp=temp;}
    public Temperature getTemp(){return temp;};
    public Weather(){}
    public void setDt(long unixTime){

            dt=unixTime;
            date=Calendar.getInstance();
            date.setTimeInMillis(dt*1000);
            dateString=(date.get(Calendar.DAY_OF_MONTH)>=10 ? date.get(Calendar.DAY_OF_MONTH) : ("0"+ date.get(Calendar.DAY_OF_MONTH)))+"."+
                    (date.get(Calendar.MONTH)+1>=10 ? (date.get(Calendar.MONTH)+1) : ("0"+(date.get(Calendar.MONTH)+1))) +"."
                    + date.get(Calendar.YEAR);
        }
    public long getDt(){return dt;}
    public Calendar getDate(){return date;}
    public String getDateString(){return dateString;}
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeString(dateString);
        parcel.writeValue(temp);
    }
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        // распаковываем объект из Parcel
        public Weather createFromParcel(Parcel in) {

            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
    private Weather(Parcel parcel) {
        dateString=parcel.readString();
        temp=(Temperature)parcel.readValue(Temperature.class.getClassLoader());
    }
    /*private long id;
    private String main;
    private String description;
    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }*/

    @Override
    public String toString() {

        return "Date:" + dateString
                 + " " + temp.toString();
    }
}
