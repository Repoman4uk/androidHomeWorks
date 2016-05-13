package com.itis.androidlab.fragmentsaffiche;

/**
 * Created by almaz_000 on 16.02.2016.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Set;

import com.itis.androidlab.fragmentsaffiche.fragments.FilmChooserFragment;
import com.itis.androidlab.fragmentsaffiche.models.Film;

public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmRecyclerAdapter.ViewHolder>{
    private List<Film> films;
    private FilmChooserFragment filmChooserFragment;
    private Set<String> willWatchPostoins;
    public FilmRecyclerAdapter(List <Film> films,FilmChooserFragment ff,Set<String> willWatchPostoins){
        filmChooserFragment=ff;
        this.films=films;
        this.willWatchPostoins=willWatchPostoins;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView filmName;
        public View view;
        public ViewHolder(View v) {
            super(v);
            view=v;
            filmName = (TextView) v.findViewById(R.id.text);
        }
    }
        @Override
    public FilmRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);


        ViewHolder vh=new ViewHolder(view);
            return vh;
    }

    @Override
    public void onBindViewHolder(FilmRecyclerAdapter.ViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filmChooserFragment.setFilm(position);
            }
        });
        holder.filmName.setText(films.get(position).getTitle());
        if (willWatchPostoins.contains(Integer.toString(position))) holder.view.setBackgroundColor(filmChooserFragment.getResources().getColor(R.color.yellow));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
}
