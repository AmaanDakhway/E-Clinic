package com.example.e_clinic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Historycheckrecfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recview;
    historyadapter historyadapter;
    String userID;

    public Historycheckrecfragment(String userID) {
        this.userID = userID;
    }

    public Historycheckrecfragment() {

    }

    public static Historycheckrecfragment newInstance(String param1, String param2) {
        Historycheckrecfragment fragment = new Historycheckrecfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_historycheckrecfragment, container, false);
        recview = view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<HistoryCheckmodel> options =
                new FirebaseRecyclerOptions.Builder<HistoryCheckmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference(userID).child("Appointments"), HistoryCheckmodel.class)
                        .build();
        historyadapter =new historyadapter(options);
        recview.setAdapter(historyadapter);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        historyadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        historyadapter.stopListening();
    }

}