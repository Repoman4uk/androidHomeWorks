package com.itis.androidlab.fragmentsaffiche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Film;

public class FilmDetailsFragment extends Fragment {

    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescriptionTextView;
    private TextView mDirectorTextView;
    private TextView mActorTextView;
    private TextView mBudgetTextView;
    private Button willWatchButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_details, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        mTitleTextView = (TextView) view.findViewById(R.id.film_title);
        mDateTextView = (TextView) view.findViewById(R.id.film_date);
        mDescriptionTextView = (TextView) view.findViewById(R.id.film_description);
        mDirectorTextView = (TextView) view.findViewById(R.id.film_director);
        mActorTextView = (TextView) view.findViewById(R.id.actor);
        mBudgetTextView = (TextView) view.findViewById(R.id.budget);
        willWatchButton = (Button) view.findViewById(R.id.button);
        willWatchButton.setVisibility(View.INVISIBLE);
        willWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FilmChooserFragment fcf=(FilmChooserFragment)fragmentManager.findFragmentById(R.id.film_chooser);
                FilmWillWatchProcessor listener = (FilmWillWatchProcessor) fcf;
                listener.onBeWatchClicked();
            }
        });
    }

    public void setFilm(Film film) {
        if (willWatchButton.getVisibility()==View.INVISIBLE) willWatchButton.setVisibility(View.VISIBLE);
        mTitleTextView.setText(film.getTitle());
        mDateTextView.setText(film.getDate());
        mDescriptionTextView.setText(film.getDescription());
        mDirectorTextView.setText(String.format(getResources().getString(R.string.film_director), film.getDirector()));
        mActorTextView.setText(String.format(getResources().getString(R.string.actor), film.getActors()));
        mBudgetTextView.setText(String.format(getResources().getString(R.string.budget), String.valueOf(film.getBudget())));
    }
    public interface FilmWillWatchProcessor {
        void onBeWatchClicked();
    }
}
