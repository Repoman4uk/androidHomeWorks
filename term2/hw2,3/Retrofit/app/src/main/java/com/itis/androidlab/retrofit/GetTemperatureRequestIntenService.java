package com.itis.androidlab.retrofit;

import android.app.IntentService;
import android.content.Intent;

import com.itis.androidlab.retrofit.models.FullWeatherInfo;
import com.itis.androidlab.retrofit.network.SessionRestManager;

/**
 * Created by almaz_000 on 13.05.2016.
 */
public class GetTemperatureRequestIntenService extends IntentService {
    FullWeatherInfo fullWeatherInfo;
    String city;
    public static final String ACTION_PROGRESS = "com.itis.androidlab.retrofit.intentservice.PROGRESS";
    public static final String EXTRA_KEY_PROGRESS = "EXTRA_PROGRESS";
    public static final String ACTION_INFO = "com.itis.androidlab.retrofit.intentservice.INFO";
    public static final String EXTRA_KEY_INFO = "EXTRA_INFO";
    public static final String ACTION_ERROR = "com.itis.androidlab.retrofit.intentservice.ERROR";
    public static final String EXTRA_KEY_ERROR = "EXTRA_ERROR";
    public GetTemperatureRequestIntenService(){
        super("cool");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Intent updateIntent = new Intent();
        updateIntent.setAction(ACTION_PROGRESS);
        updateIntent.addCategory(Intent.CATEGORY_DEFAULT);
        updateIntent.putExtra(EXTRA_KEY_PROGRESS, 0);
        sendBroadcast(updateIntent);
        //showProgressBar();
    }
    @Override
    public void onHandleIntent(Intent intent){
        try {
            city = intent.getStringExtra("city");
            fullWeatherInfo = SessionRestManager.getInstance().getRest().getTemperatureByCity(city);
            Intent updateIntent = new Intent();
            updateIntent.setAction(ACTION_INFO);
            updateIntent.addCategory(Intent.CATEGORY_DEFAULT);
            updateIntent.putExtra(EXTRA_KEY_INFO, fullWeatherInfo);
            sendBroadcast(updateIntent);
        }
        catch(Exception e){
            Intent errorIntent=new Intent();
            errorIntent.setAction(ACTION_ERROR);
            errorIntent.addCategory(Intent.CATEGORY_DEFAULT);
            errorIntent.putExtra(EXTRA_KEY_ERROR,e.toString());
            sendBroadcast(errorIntent);


            }

    }
    @Override
    public void onDestroy(){
        Intent updateIntent = new Intent();
        updateIntent.setAction(ACTION_PROGRESS);
        updateIntent.addCategory(Intent.CATEGORY_DEFAULT);
        updateIntent.putExtra(EXTRA_KEY_PROGRESS, 1);
        sendBroadcast(updateIntent);
        //recView.setAdapter(new RecAdapter(fullWeatherInfo.getWeather()));
        //hideProgressBar();
    }

}