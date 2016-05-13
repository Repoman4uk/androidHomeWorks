package com.itis.androidlab.fragmentsaffiche.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.androidlab.fragmentsaffiche.FilmRecyclerAdapter;
import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmChooserFragment extends Fragment implements View.OnClickListener,FilmDetailsFragment.FilmWillWatchProcessor {

    private List<Film> mFilms;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recycleLayoutManager;
    private int currentPosition;
    private Set<String> willWatchedPositions;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_chooser, container, false);
        mFilms = readFilmsFromJson();


        sharedPreferences=getActivity().getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        willWatchedPositions=new HashSet<String>(sharedPreferences.getStringSet("positions", new HashSet<String>()));
        initViews(view);
        //setFilm(0);
        return view;
    }
    private void initViews(View view) {
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
        recycleLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recycleLayoutManager);
        recyclerAdapter=new FilmRecyclerAdapter(mFilms,this,willWatchedPositions);
        recyclerView.setAdapter(recyclerAdapter);

    }

    private List<Film> readFilmsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Film.FilmArray filmArray = mapper.readValue(getActivity().getAssets().open("films.json"),
                    new TypeReference<Film.FilmArray>() {
                    });
            return filmArray.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        /*Film film = null;
        switch (v.getId()) {
            case R.id.film1:
                film = mFilms.get(0);
                break;
            case R.id.film2:
                film = mFilms.get(1);
                break;
        }
        FilmChooserProcessor listener = (FilmChooserProcessor) getActivity();
        listener.onFilmChosen(film);*/
    }
    public void setFilm(int position)
    {
        currentPosition=position;
        FilmChooserProcessor listener = (FilmChooserProcessor) getActivity();
        listener.onFilmChosen(mFilms.get(position));
        //recyclerView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.yellow));
    }
    public interface FilmChooserProcessor {
        void onFilmChosen(Film film);
    }
    @Override
    public void onBeWatchClicked(){
        String pos=Integer.toString(currentPosition);
        if (willWatchedPositions.contains(pos)){
            willWatchedPositions.remove(pos);
            recyclerView.getChildAt(currentPosition).setBackgroundColor(getResources().getColor(R.color.white));

        }
        else{
            willWatchedPositions.add(pos);
            recyclerView.getChildAt(currentPosition).setBackgroundColor(getResources().getColor(R.color.yellow));
        }
        SharedPreferences.Editor edit=sharedPreferences.edit();
        edit.remove("positions");
        edit.putStringSet("positions",willWatchedPositions);
        edit.commit();
    }

}
