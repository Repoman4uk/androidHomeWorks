package com.itis.androidlab.butterknife;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.OnClick;
import butterknife.ButterKnife;

public class FindViewByIdActivity extends AppCompatActivity {

    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplay = (TextView) findViewById(R.id.display);
        mDisplay.setText(getResources().getString(R.string.findbyid_display_text_message1));
        ButterKnife.bind(this);
        }
    @OnClick(R.id.next_button)
    public void listener(View view) {
        mDisplay.setText(getResources().getString(R.string.findbyid_display_text_message2));
        view.setEnabled(false);
        view.setBackgroundColor(ContextCompat.getColor(FindViewByIdActivity.this, R.color.colorAccent));
    }
        /*findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDisplay.setText(getResources().getString(R.string.findbyid_display_text_message2));
                view.setEnabled(false);
                view.setBackgroundColor(ContextCompat.getColor(FindViewByIdActivity.this, R.color.colorAccent));
            }
        });*/
}


