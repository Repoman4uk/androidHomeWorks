package com.itis.androidlab.retrofit.activities;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.itis.androidlab.retrofit.GetTemperatureRequestIntenService;
import com.itis.androidlab.retrofit.R;
import com.itis.androidlab.retrofit.models.FullWeatherInfo;
import com.itis.androidlab.retrofit.models.Temperature;
import com.itis.androidlab.retrofit.models.Weather;
import com.itis.androidlab.retrofit.network.SessionRestManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycleView)
    RecyclerView recView;
    FullWeatherInfo fullWeatherInfo;
    private MaterialDialog mDialog;
    InfoBroadcastReceiver infoBroadcastReceiver;
    ProgressBroadcastReceiver progressBroadcastReceiver;
    ErrorBroadcastReceiver errorBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        /*GetTemperatureRequest getTemperatureRequest = new GetTemperatureRequest();
        getTemperatureRequest.execute("Kazan");*/
        //startService(new Intent(this,GetTemperatureRequestIntenService.class).putExtra("city","Kazan"));
        recView.setLayoutManager(new LinearLayoutManager(this));
        Intent newInt = new Intent(this,GetTemperatureRequestIntenService.class);
        startService(newInt.putExtra("city", "Kazan"));
        infoBroadcastReceiver = new InfoBroadcastReceiver();
        progressBroadcastReceiver = new ProgressBroadcastReceiver();
        IntentFilter infoFilter = new IntentFilter(
                GetTemperatureRequestIntenService.ACTION_INFO);
        infoFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(infoBroadcastReceiver, infoFilter);
        IntentFilter progressFilter = new IntentFilter(
                GetTemperatureRequestIntenService.ACTION_PROGRESS);
        progressFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(progressBroadcastReceiver, progressFilter);
        errorBroadcastReceiver = new ErrorBroadcastReceiver();
        IntentFilter errorFilter = new IntentFilter(
                GetTemperatureRequestIntenService.ACTION_ERROR);
        errorFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(errorBroadcastReceiver, errorFilter);


    }
    public class ErrorBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String er=intent.getStringExtra(GetTemperatureRequestIntenService.EXTRA_KEY_ERROR);
            Toast.makeText(getApplicationContext(),er,Toast.LENGTH_LONG).show();
        }
    }
    public class InfoBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            fullWeatherInfo=intent.getParcelableExtra(GetTemperatureRequestIntenService.EXTRA_KEY_INFO);

        }
    }
    public class ProgressBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            int key=intent.getIntExtra(GetTemperatureRequestIntenService.EXTRA_KEY_PROGRESS,0);
            if (key==0) showProgressBar();
            else
            {
                if (fullWeatherInfo!=null) recView.setAdapter(new RecAdapter(fullWeatherInfo.getWeather()));
                hideProgressBar();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(infoBroadcastReceiver);
        unregisterReceiver(progressBroadcastReceiver);
    }

    public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{
        List<Weather> weathers;
        public RecAdapter(List<Weather> weathers){this.weathers=weathers;}
        public class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.dateText)
            public TextView dateText;
            @Bind(R.id.morningText)
            public TextView morningText;
            @Bind(R.id.dayText)
            public TextView dayText;
            @Bind(R.id.eveningText)
            public TextView eveningText;
            @Bind(R.id.nigthText)
            public TextView nightText;
            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this,v);
            }
        }
        @Override
        public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_item, parent, false);


            ViewHolder vh=new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecAdapter.ViewHolder holder, final  int position){
            Weather w = weathers.get(position);
            holder.dateText.setText(w.getDateString());
            Temperature t=w.getTemp();
            int morningTemp=t.getMorning().intValue();
            int dayTemp=t.getDay().intValue();
            int eveningTemp=t.getEvening().intValue();
            int nightTemp=t.getNight().intValue();
            holder.morningText.setText("Утро: "+(morningTemp>0?"+"+morningTemp:morningTemp));
            holder.dayText.setText("День: "+(dayTemp>0?"+"+dayTemp:dayTemp));
            holder.eveningText.setText("Вечер: "+(eveningTemp>0?"+"+eveningTemp:eveningTemp));
            holder.nightText.setText("Ночь: "+(nightTemp>0?"+"+nightTemp:nightTemp));

        }
        @Override
        public int getItemCount(){
            return weathers.size();
        }
    }
    /*public class GetTemperatureRequest extends AsyncTask<String, Void, FullWeatherInfo> {
        @Override
        protected void onPreExecute() {
            showProgressBar();
        }

        @Override
        protected FullWeatherInfo doInBackground(String... city) {

            return SessionRestManager.getInstance().getRest().getTemperatureByCity(city[0]);

        }

        @Override
        protected void onPostExecute(FullWeatherInfo fullWeatherInfo) {
            recView.setAdapter(new RecAdapter(fullWeatherInfo.getWeather()));
            hideProgressBar();
        }
    }*/


    protected void showProgressBar() {
        if (mDialog == null) {
            mDialog = new MaterialDialog.Builder(MainActivity.this)
                    .content(R.string.content_loading)
                    .progress(true, 0)
                    .cancelable(false)
                    .show();
        } else {
            mDialog.show();
        }
    }

    protected void hideProgressBar() {
        if (mDialog != null)
            mDialog.dismiss();
    }


}
