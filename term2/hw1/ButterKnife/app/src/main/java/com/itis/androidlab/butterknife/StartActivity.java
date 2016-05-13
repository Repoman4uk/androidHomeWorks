package com.itis.androidlab.butterknife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @BindString(R.string.start_toast_todo) String mToastText;

    @OnClick(R.id.find_by_id_button)
    public void startFindViewByIdActivity() {
        startActivity(new Intent(StartActivity.this, FindViewByIdActivity.class));
    }

    @OnClick(R.id.butter_knife_button)
    public void startButterKnifeActivity() {
        // TODO реализовать activity, используя ButterKnife
        startActivity(new Intent(StartActivity.this, RecyclerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(StartActivity.this);
    }
}
