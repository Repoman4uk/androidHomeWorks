package com.itis.androidlab.fragmentsaffiche.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Cinema;
import com.itis.androidlab.fragmentsaffiche.CinemaRecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CinemasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView cinemaRecyclerView;
    private RecyclerView.Adapter cinemaRecyclerAdapter;
    private OnFragmentInteractionListener mListener;

    private List<Cinema> allCinemas;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CinemasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CinemasFragment newInstance(String param1, String param2) {
        CinemasFragment fragment = new CinemasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CinemasFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cinemas, container, false);
        allCinemas = readCinemasFromJson();
        Bundle args=getArguments();
        if (args!=null) {
            long id = args.getLong("filmID");

            List<Cinema> cinemas = new ArrayList<>();
            for (Cinema cinema : allCinemas) {
                if (cinema.getFilms().contains(id)) cinemas.add(cinema);
            }
            initViews(view,cinemas);
        }
        else initViews(view,allCinemas);
        return view;
    }

    public void initViews(View view, List<Cinema> cinemas){
        cinemaRecyclerView=(RecyclerView) view.findViewById(R.id.cinemasRecycleView);
        LinearLayoutManager recycleLayoutManager=new LinearLayoutManager(getActivity());
        cinemaRecyclerView.setLayoutManager(recycleLayoutManager);
        cinemaRecyclerAdapter=new CinemaRecyclerAdapter(cinemas);
        cinemaRecyclerView.setAdapter(cinemaRecyclerAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
