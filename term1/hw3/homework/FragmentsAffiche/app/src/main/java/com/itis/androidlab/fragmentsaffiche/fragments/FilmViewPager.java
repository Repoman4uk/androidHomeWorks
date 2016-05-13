package com.itis.androidlab.fragmentsaffiche.fragments;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Genre;

/**
 * Created by almaz_000 on 29.02.2016.
 */
public class FilmViewPager extends Fragment {
    static final int PAGE_COUNT = 4;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.film_view_pager, container, false);
        pager=(ViewPager)view.findViewById(R.id.filmViewPager);
        pagerAdapter=new FilmFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter((pagerAdapter));

        return view;
    }
    private class FilmFragmentPagerAdapter extends FragmentPagerAdapter {
        public FilmFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FilmsListFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            String[] itemsName=getResources().getStringArray(R.array.items);
            return itemsName[position];
        }
    }
}
