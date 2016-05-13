package com.itis.androidlab.fragmentsaffiche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Cinema;
import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmChooserFragment extends Fragment implements View.OnClickListener,FilmDetailsFragment.CinemasWithFilmProcessor {

    private List<Film> mFilms;
    private List<Cinema> mCinemas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_chooser, container, false);
        mFilms = readFilmsFromJson();
        mCinemas=readCinemasFromJson();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        Button film1 = (Button) view.findViewById(R.id.film1);
        film1.setText(mFilms.get(0).getTitle());
        film1.setOnClickListener(this);

        Button film2 = (Button) view.findViewById(R.id.film2);
        film2.setText(mFilms.get(1).getTitle());
        film2.setOnClickListener(this);

        Button cinemasButton=(Button) view.findViewById(R.id.cinemasButton);
        cinemasButton.setOnClickListener(this);
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
    private List<Cinema> readCinemasFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cinema.CinemaArray cinemaArray = mapper.readValue(getActivity().getAssets().open("cinemas.json"),
                    new TypeReference<Cinema.CinemaArray>() {
                    });
            return cinemaArray.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        Film film = null;
        FilmChooserProcessor listener = (FilmChooserProcessor) getActivity();
        ShowCinemasProcessor listenerCinemas=(ShowCinemasProcessor) getActivity();
        switch (v.getId()) {
            case R.id.film1:
                film = mFilms.get(0);
                listener.onFilmChosen(film);
                break;
            case R.id.film2:
                film = mFilms.get(1);
                listener.onFilmChosen(film);
                break;
            case R.id.cinemasButton:
                listenerCinemas.onCinemasChoiced(mCinemas);
        }

    }


    public interface FilmChooserProcessor {
        void onFilmChosen(Film film);
    }
    public interface ShowCinemasProcessor{
        void onCinemasChoiced(List<Cinema> cinemas);
    }
    @Override
    public  void onDetailsCinemasPressed(int filmId){
        List<Cinema> newCinemaList=new ArrayList<>();
        for (Cinema cinema:mCinemas){
            if (cinema.getFilms().contains(filmId)) newCinemaList.add(cinema);
        }
        ShowCinemasProcessor listenerCinemas=(ShowCinemasProcessor) getActivity();
        listenerCinemas.onCinemasChoiced(newCinemaList);
    }
}
