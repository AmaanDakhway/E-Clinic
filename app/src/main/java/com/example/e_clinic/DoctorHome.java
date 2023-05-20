package com.example.e_clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorHome extends AppCompatActivity {

    TextView textView;
    Button checkappointment, enterdocument, viewdocument;
    String Name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        checkappointment = findViewById(R.id.checkappointment);
        textView = findViewById(R.id.textView22);
        enterdocument = findViewById(R.id.enterdocument);
        viewdocument = findViewById(R.id.ViewDocumentsdoc);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DoctorRegistered");
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Name = snapshot.child("Name").getValue().toString();
                textView.setText("Welcome Back Dr. "+Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        checkappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CheckAppointment.class);
                intent.putExtra("NAME",Name);
                startActivity(intent);
            }
        });

        enterdocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EnterDocument.class));
            }
        });

        viewdocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewDocuments.class));
            }
        });

    }

    public void onBackPressed(){
        this.finishAffinity();
    }

}