package com.itis.androidlab.fragmentsaffiche;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itis.androidlab.fragmentsaffiche.models.Cinema;

import java.util.List;

/**
 * Created by almaz_000 on 18.02.2016.
 */
public class CinemaRecyclerAdapter extends RecyclerView.Adapter<CinemaRecyclerAdapter.ViewHolder>{
    private List<Cinema> cinemas;

    public CinemaRecyclerAdapter(List<Cinema> cinemas){
        this.cinemas=cinemas;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cinemaName;
        public TextView cinemaAddress;
        public View view;
        public ViewHolder(View v) {
            super(v);
            view=v;
            cinemaName = (TextView) v.findViewById(R.id.cinemaName);
            cinemaAddress = (TextView) v.findViewById(R.id.cinemaAddress);
        }
    }
    @Override
    public CinemaRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cinema_recycle_view_item, parent, false);


        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CinemaRecyclerAdapter.ViewHolder holder, int position) {
        holder.cinemaName.setText(cinemas.get(position).getName());
        holder.cinemaAddress.setText(cinemas.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return cinemas.size();
    }
}