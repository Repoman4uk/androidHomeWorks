package com.itis.androidlab.fragmentsaffiche;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.util.List;
import java.util.Set;

/**
 * Created by almaz_000 on 29.02.2016.
 */
public class FilmsRecyclerViewAdapter extends RecyclerView.Adapter<FilmsRecyclerViewAdapter.ViewHolder>{
    private List<Film> films;
    FragmentActivity activityFragment;
    public FilmsRecyclerViewAdapter(List <Film> films, FragmentActivity activityFragment){
        this.activityFragment=activityFragment;
        this.films=films;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView filmName;
        public TextView filmDate;
        public View view;
        public ViewHolder(View v) {
            super(v);
            view=v;
            filmName = (TextView) v.findViewById(R.id.filmNameRecyclerViewItemText);
            filmDate = (TextView) v.findViewById(R.id.filmDateRecyclerViewItemText);

        }
    }
    @Override
    public FilmsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.films_list_item, parent, false);


        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(FilmsRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.filmDate.setText(films.get(position).getDate());
        holder.filmName.setText(films.get(position).getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnFilmChoosenListener listener=(OnFilmChoosenListener) activityFragment;
                listener.onFilmChoosen(films.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
    public interface OnFilmChoosenListener{
        public void onFilmChoosen(Film film);
    }
}
