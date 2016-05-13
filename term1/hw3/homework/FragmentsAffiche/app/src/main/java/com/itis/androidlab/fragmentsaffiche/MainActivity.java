package com.itis.androidlab.fragmentsaffiche;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itis.androidlab.fragmentsaffiche.fragments.CinemasFragment;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmDetailsFragment;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmViewPager;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmsListFragment;
import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilmsRecyclerViewAdapter.OnFilmChoosenListener,FilmDetailsFragment.CinemasWithFilmProcessor{
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    ArrayList<String> itemNames=new ArrayList<>();
    ArrayList<Integer> imageIDs=new ArrayList<>();
    ListView drawerListView;
    DrawerListAdapter drawerListAdapter;
    FilmViewPager filmViewPager;
    CinemasFragment cinemasFragment;
    public class DrawerListAdapter extends BaseAdapter{
        Context ctx;
        LayoutInflater lInflater;
        ArrayList<String> itemNames;
        ArrayList<Integer> imageIDs;


        DrawerListAdapter(Context context, ArrayList<String> itemNames,ArrayList<Integer> imageIDs){
            ctx=context;
            this.itemNames=itemNames;
            this.imageIDs=imageIDs;
            lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getCount() {
            return itemNames.size();
        }
        @Override
        public Object getItem(int position) {
            return itemNames.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = convertView;
            if (view == null) {
                view = lInflater.inflate(R.layout.drawer_list_item, parent, false);
            }
            ((TextView) view.findViewById(R.id.drawerItemtextView)).setText(itemNames.get(position));
            ((ImageView)view.findViewById(R.id.drawerItemImageView)).setImageResource(imageIDs.get(position));
            return view;
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle=new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                null,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        fillItemsList();
        drawerListAdapter=new DrawerListAdapter(this,itemNames,imageIDs);
        drawerListView=(ListView)findViewById(R.id.left_drawer);
        drawerListView.setAdapter(drawerListAdapter);
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setTitle("Фильмы");
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        filmViewPager=new FilmViewPager();
        ft.replace(R.id.content_frame,filmViewPager);
        ft.commit();
    }
    private void fillItemsList(){
        imageIDs.add(R.drawable.movie);
        imageIDs.add(R.drawable.cinema);
        itemNames.add("Фильмы");
        itemNames.add("Кинотеатры");

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction ft = fragmentManager.beginTransaction();
            switch (position){
                case 0:
                    getSupportActionBar().setTitle("Фильмы");

                    filmViewPager=new FilmViewPager();
                    ft.replace(R.id.content_frame,filmViewPager);
                    ft.commit();
                    break;
                case 1:


                    cinemasFragment = new CinemasFragment();

                    ft.replace(R.id.content_frame,cinemasFragment);
                    ft.commit();
                    getSupportActionBar().setTitle("Кинотеатры");
                    break;
            }
            mDrawerLayout.closeDrawer(drawerListView);
        }
    }
    @Override
    public void onFilmChoosen(Film film){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        FilmDetailsFragment filmDetailsFragment = new FilmDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FilmDetailsFragment.FILM, film);
        filmDetailsFragment.setArguments(bundle);
        ft.replace(R.id.content_frame, filmDetailsFragment);
        ft.commit();
        getSupportActionBar().setTitle("Фильмы");
    }
    @Override
    public void onDetailsCinemasPressed(long id){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        CinemasFragment cinemasFragment = new CinemasFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("filmID", id);
        cinemasFragment.setArguments(bundle);
        ft.replace(R.id.content_frame, cinemasFragment);
        ft.commit();
        getSupportActionBar().setTitle("Кинотеатры");
    }

}
