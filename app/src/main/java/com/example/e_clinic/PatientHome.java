package com.example.e_clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientHome extends AppCompatActivity {

    Button bookappointment, checkapointment, map, viewdocumemts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        bookappointment = findViewById(R.id.bookappointment);
        checkapointment = findViewById(R.id.checkappointment);
        map    = findViewById(R.id.covidcenters);
        viewdocumemts = findViewById(R.id.ViewDocumentspat);

        viewdocumemts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewDocuments.class));
            }
        });

        bookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SelectDoctor.class));
            }
        });
        checkapointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HistoryAppointment.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CenterLocation.class));
            }
        });
    }
    public void onBackPressed(){
        this.finishAffinity();
    }
}