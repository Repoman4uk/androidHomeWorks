package com.itis.androidlab.fragmentsaffiche;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itis.androidlab.fragmentsaffiche.fragments.FilmChooserFragment;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmDetailsFragment;
import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmChooserFragment.FilmChooserProcessor{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFilmChosen(Film film) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FilmDetailsFragment filmDetailsFragment = (FilmDetailsFragment) fragmentManager
                .findFragmentById(R.id.film_details);

        if (filmDetailsFragment != null)
            filmDetailsFragment.setFilm(film);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
