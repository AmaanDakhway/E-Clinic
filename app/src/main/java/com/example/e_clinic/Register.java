package com.example.e_clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    Spinner select;
    String[] Select = { "Doctor", "Patient",};
    String DocPat = "";
    EditText mFullName,mEmail,mPassword,mPhone,mDegree,mExperience;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName   = findViewById(R.id.fullName);
        mEmail      = findViewById(R.id.Email);
        mPassword   = findViewById(R.id.password);
        mPhone      = findViewById(R.id.phone);
        mDegree     = findViewById(R.id.degree);
        mExperience = findViewById(R.id.experience);
        mRegisterBtn= findViewById(R.id.registerBtn);
        mLoginBtn   = findViewById(R.id.createText);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        select = findViewById(R.id.select);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_item, Select);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(adapter1);

        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String check = Select[i];
                Toast.makeText(getApplicationContext(),check,Toast.LENGTH_SHORT).show();
                if(check.equalsIgnoreCase("doctor")){
                    mDegree.setVisibility(view.VISIBLE);
                    mExperience.setVisibility(view.VISIBLE);
                }
                if(check.equalsIgnoreCase("patient")){
                    mDegree.setVisibility(view.INVISIBLE);
                    mExperience.setVisibility(view.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                DocPat = select.getSelectedItem().toString();

                if (DocPat.equalsIgnoreCase("Doctor")){
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "User Created.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                String userID = fAuth.getCurrentUser().getUid();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("DoctorRegistered").child(userID);
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("Name",mFullName.getText().toString());
                                hashMap.put("EmailID",mEmail.getText().toString());
                                hashMap.put("ContactNumber",mPhone.getText().toString());
                                hashMap.put("Degree",mDegree.getText().toString());
                                hashMap.put("Experience",mExperience.getText().toString());


                                databaseReference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "User Created.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                String userID = fAuth.getCurrentUser().getUid();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PatientRegistered").child(userID);
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("Name",mFullName.getText().toString());
                                hashMap.put("EmailID",mEmail.getText().toString());
                                hashMap.put("ContactNumber",mPhone.getText().toString());

                                databaseReference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}