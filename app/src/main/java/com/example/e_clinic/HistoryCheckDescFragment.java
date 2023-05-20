package com.example.e_clinic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HistoryCheckDescFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String age, bloodGroup, date, doctor, email, gender, name, phone, symptoms, time;

    public HistoryCheckDescFragment() {
        // Required empty public constructor
    }

    public HistoryCheckDescFragment(String age, String bloodGroup, String date, String doctor, String email, String gender, String name, String phone, String symptoms, String time) {
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.date = date;
        this.doctor = doctor;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.symptoms = symptoms;
        this.time = time;
    }

    public static HistoryCheckDescFragment newInstance(String param1, String param2) { HistoryCheckDescFragment fragment = new HistoryCheckDescFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_check_desc, container, false);

        TextView age1, bloodGroup1, date1, doctor1, email1, gender1, name1, phone1, symptoms1, time1;

        name1 = view.findViewById(R.id.name);
        age1  = view.findViewById(R.id.age);
        bloodGroup1 = view.findViewById(R.id.bloodGroup);
        date1 = view.findViewById(R.id.date);
        doctor1 = view.findViewById(R.id.doctor);
        email1 = view.findViewById(R.id.email);
        gender1 = view.findViewById(R.id.gender);
        phone1 = view.findViewById(R.id.phone);
        symptoms1 = view.findViewById(R.id.symptoms);
        time1 = view.findViewById(R.id.time);

        name1.setText(name);
        age1.setText(age);
        bloodGroup1.setText(bloodGroup);
        date1.setText(date);
        doctor1.setText(doctor);
        email1.setText(email);
        gender1.setText(gender);
        phone1.setText(phone);
        symptoms1.setText(symptoms);
        time1.setText(time);


        return view;

    }
    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper1,new Historycheckrecfragment()).addToBackStack(null).commit();

    }
}