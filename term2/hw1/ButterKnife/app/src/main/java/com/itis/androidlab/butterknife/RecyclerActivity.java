package com.itis.androidlab.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class RecyclerActivity extends AppCompatActivity {
    @Bind(R.id.recycleView) RecyclerView recView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
        String[] menuItems=getResources().getStringArray(R.array.recyclerItems);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(new RecyclerAdapter(menuItems));
    }
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        private String[] items;
        public RecyclerAdapter(String[] items){this.items= items;}
        public class ViewHolder extends RecyclerView.ViewHolder{
            @Bind(R.id.textView)
            TextView txtView;
            public ViewHolder(View v){
                super(v);
                ButterKnife.bind(this,v);

            }
        }
        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);
            ViewHolder vh=new ViewHolder(view);
            return vh;
        }
        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position){
            holder.txtView.setText(items[position]);
        }
        @Override
        public int getItemCount(){return items.length;}


    }
}
