package com.example.e_clinic;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DescFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String Name, EmailID, ContactNumber, Degree, Experience;

    public DescFragment() {
        // Required empty public constructor
    }
    public DescFragment(String Name, String ContactNumber, String EmailID, String Degree, String Experience) {
        this.Name = Name;
        this.ContactNumber = ContactNumber;
        this.EmailID = EmailID;
        this.Degree = Degree;
        this.Experience = Experience;
    }

    public static DescFragment newInstance(String param1, String param2) {
        DescFragment fragment = new DescFragment();
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
        View view = inflater.inflate(R.layout.fragment_desc, container, false);

        TextView textView4 = view.findViewById(R.id.textView4);
        TextView textView5 = view.findViewById(R.id.textView5);
        TextView textView6 = view.findViewById(R.id.textView6);
        TextView textView7 = view.findViewById(R.id.textView7);
        TextView textView8 = view.findViewById(R.id.textView8);
        Button button = view.findViewById(R.id.button2);

        textView4.setText(Name);
        textView5.setText(ContactNumber);
        textView6.setText(EmailID);
        textView7.setText(Degree);
        textView8.setText(Experience);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(),BookAppointment.class);
                intent.putExtra("NAME",Name);
                startActivity(intent);
            }
        });

        return view;

    }
    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).addToBackStack(null).commit();

    }
}