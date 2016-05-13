package com.itis.androidlab.fragmentsaffiche.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.androidlab.fragmentsaffiche.R;

import com.itis.androidlab.fragmentsaffiche.models.Film;
import com.itis.androidlab.fragmentsaffiche.FilmsRecyclerViewAdapter;
import com.itis.androidlab.fragmentsaffiche.models.Genre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmsListFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    private List<Film> mFilms;
    private RecyclerView filmsRecyclerView;
    private RecyclerView.Adapter filmsRecyclerAdapter;
    private LinearLayoutManager recycleLayoutManager;
    List<Film> films;
    static FilmsListFragment newInstance(int page) {
        FilmsListFragment filmsListFragment = new FilmsListFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        filmsListFragment.setArguments(arguments);
        return filmsListFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.films_list_fragment, container, false);
        mFilms=readFilmsFromJson();

        Bundle args=getArguments();
        if (args!=null){
            int key=args.getInt(ARGUMENT_PAGE_NUMBER);
            films=new ArrayList<>();
            switch (key){
                case 0:
                   films=mFilms;
                    break;
                case 1:
                    for (Film film:mFilms) {
                        if (film.getGenres().contains(Genre.ACTION)) films.add(film);
                    }
                    break;
                case 2:
                    for (Film film:mFilms) {
                        if (film.getGenres().contains(Genre.CARTOON)) films.add(film);
                    }
                    break;
                case 3:
                    for (Film film:mFilms) {
                        if (film.getGenres().contains(Genre.FANTASTIC)) films.add(film);
                    }
                    break;
            }
            filmsRecyclerView=(RecyclerView) view.findViewById(R.id.filmsRecyclerView);
            recycleLayoutManager=new LinearLayoutManager(getActivity());
            recycleLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            filmsRecyclerView.setLayoutManager(recycleLayoutManager);
            filmsRecyclerAdapter=new FilmsRecyclerViewAdapter(films,getActivity());
            filmsRecyclerView.setAdapter(filmsRecyclerAdapter);

        }
        else{
            filmsRecyclerView=(RecyclerView) view.findViewById(R.id.filmsRecyclerView);
            recycleLayoutManager=new LinearLayoutManager(getActivity());
            recycleLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            filmsRecyclerView.setLayoutManager(recycleLayoutManager);
            filmsRecyclerAdapter=new FilmsRecyclerViewAdapter(mFilms,getActivity());
            filmsRecyclerView.setAdapter(filmsRecyclerAdapter);
        }
        return view;
    }
    private List<Film> readFilmsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,true);
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
}
