package com.itis.androidlab.fragmentsaffiche;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.itis.androidlab.fragmentsaffiche.fragments.CinemasFragment;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmChooserFragment;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmDetailsFragment;
import com.itis.androidlab.fragmentsaffiche.models.Cinema;
import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FilmChooserFragment.FilmChooserProcessor,FilmChooserFragment.ShowCinemasProcessor {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFilmChosen(Film film) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        FilmDetailsFragment filmDetailsFragment = new FilmDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FilmDetailsFragment.FILM, film);
        filmDetailsFragment.setArguments(bundle);
        if (fragmentManager.findFragmentById(R.id.film_details) != null) {
            ft.replace(R.id.film_details, filmDetailsFragment, "fragment2");
            ft.commit();
        } else {
            ft.add(R.id.film_details, filmDetailsFragment, FilmDetailsFragment.class.getSimpleName());
            ft.commit();
        }
    }
    @Override
    public void onCinemasChoiced(List<Cinema> cinemas){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        CinemasFragment cinemasFragment=new CinemasFragment();
        cinemasFragment.setCinemas(cinemas);
        if (fragmentManager.findFragmentById(R.id.film_details) != null) {
            ft.replace(R.id.film_details, cinemasFragment, "fragment3");
            ft.commit();
        } else {
            ft.add(R.id.film_details, cinemasFragment, FilmDetailsFragment.class.getSimpleName());
            ft.commit();
        }
    }
}
