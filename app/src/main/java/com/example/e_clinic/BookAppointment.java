package com.example.e_clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {

    TextView textView6;
    Button b1,button;
    EditText textDate,textTime,textSymptoms,textBloodGroup,textName,textAge,textEmail,textPhone;
    Calendar calendar;
    Member member;
    RadioGroup radioGroup;
    DatabaseReference reff,databaseReference;
    Spinner spinner1;
    RadioGroup radioSexGroup;
    private static final int PERMISSION_REQUEST_CODE = 1;
    RadioButton radioSexButton;
    String[] text1 = { "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "05:00", "05:30", "06:00", "07:00" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        textView6 = findViewById(R.id.textView6);
        textView6.setText(getIntent().getStringExtra("NAME"));
        button         = findViewById(R.id.button);
        b1             = findViewById(R.id.btn1);
        spinner1       = findViewById(R.id.spinner1);
        textDate       = findViewById(R.id.textDate);
        textTime       = findViewById(R.id.textTime);
        textSymptoms   = findViewById(R.id.textSymptoms);
        textBloodGroup = findViewById(R.id.textBloodGroup);
        textName       = findViewById(R.id.textName);
        textAge        = findViewById(R.id.textAge);
        textEmail      = findViewById(R.id.textEmail);
        textPhone      = findViewById(R.id.textPhone);
        member         = new Member();
        radioSexGroup  = findViewById(R.id.radioGroup);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(BookAppointment.this, android.R.layout.simple_spinner_item, text1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(onItemSelectedListener1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int Year = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        textDate.setText(dayOfMonth + "-" + month + "-" + year);
                    }
                }, Year,Month,Day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId=radioSexGroup.getCheckedRadioButtonId();
                radioSexButton=(RadioButton)findViewById(selectedId);

                if (TextUtils.isEmpty(textDate.getText().toString().trim())){
                    textDate.setError("Date is Required");
                    return;
                }
                if (TextUtils.isEmpty(textTime.getText().toString().trim())){
                    textTime.setError("Time is Required");
                    return;
                }
                if (TextUtils.isEmpty(textSymptoms.getText().toString().trim())){
                    textSymptoms.setError("Symptoms is Required");
                    return;
                }
                if (TextUtils.isEmpty(textBloodGroup.getText().toString().trim())){
                    textBloodGroup.setError("BloodGroup is Required");
                }

                if (TextUtils.isEmpty(textName.getText().toString().trim())){
                    textName.setError("Name is Required");
                    return;
                }

                if (TextUtils.isEmpty(textAge.getText().toString().trim())){
                    textAge.setError("Age is Required");
                    return;
                }

                if (TextUtils.isEmpty(textEmail.getText().toString().trim())){
                    textEmail.setError("Email is Required");
                    return;
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userID = user.getUid();

                databaseReference = FirebaseDatabase.getInstance().getReference(userID).child("Appointments");
                reff = FirebaseDatabase.getInstance().getReference().child(textView6.getText().toString().trim());
                reff.orderByChild("date").equalTo(textDate.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            reff = FirebaseDatabase.getInstance().getReference().child(textView6.getText().toString().trim());
                            reff.orderByChild("time").equalTo(textTime.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()){
                                        Toast.makeText(getApplicationContext(),"The Time Is Already Taken.", Toast.LENGTH_SHORT).show();
                                    }
                                    else{

                                        member.setDate(textDate.getText().toString().trim());
                                        member.setTime(textTime.getText().toString().trim());
                                        member.setSymptoms(textSymptoms.getText().toString().trim());
                                        member.setBloodGroup(textBloodGroup.getText().toString().trim());
                                        member.setName(textName.getText().toString().trim());
                                        member.setAge(textAge.getText().toString().trim());
                                        member.setEmail(textEmail.getText().toString().trim());
                                        member.setPhone(textPhone.getText().toString().trim());
                                        member.setDoctor(textView6.getText().toString().trim());
                                        member.setGender(radioSexButton.getText().toString().trim());
                                        reff.push().setValue(member);
                                        databaseReference.push().setValue(member);

                                        String date = textDate.getText().toString().trim();
                                        String time = textTime.getText().toString().trim();
                                        String symptoms = textSymptoms.getText().toString().trim();
                                        String blood = textBloodGroup.getText().toString().trim();
                                        String name = textName.getText().toString().trim();
                                        String age = textAge.getText().toString().trim();
                                        String email = textEmail.getText().toString().trim();
                                        String phone = textPhone.getText().toString().trim();
                                        String record = textView6.getText().toString().trim();
                                        String gender = radioSexButton.getText().toString().trim();

                                        Intent intent = new Intent(getApplicationContext(), AppointmentBooked.class);
                                        intent.putExtra("DATE",date);
                                        intent.putExtra("TIME",time);
                                        intent.putExtra("SYMPTOMS",symptoms);
                                        intent.putExtra("BLOOD",blood);
                                        intent.putExtra("NAME",name);
                                        intent.putExtra("AGE",age);
                                        intent.putExtra("EMAIL",email);
                                        intent.putExtra("PHONE",phone);
                                        intent.putExtra("DOCTOR",record);
                                        intent.putExtra("GENDER", gender);
                                        startActivity(intent);

                                        Toast.makeText(BookAppointment.this,"Your Appointment Has Been Booked",Toast.LENGTH_SHORT).show();
                                        ////////////////////////SEND SMS/////////////////////////////////////
                                        String sms = textName.getText().toString()+" your appointment has been booked for "
                                                + textDate.getText().toString()+" at Time "+ textTime.getText().toString()
                                                +" with Doctor. " +textView6.getText().toString();
                                        String phoneNum = textPhone.getText().toString();
                                        if(!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                                            if(checkPermission()) {

//Get the default SmsManager//

                                                SmsManager smsManager = SmsManager.getDefault();

//Send the SMS//

                                                smsManager.sendTextMessage(phoneNum, null, sms, null, null);
                                            }else {
                                                Toast.makeText(BookAppointment.this, "Permission denied", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else{

                            member.setDate(textDate.getText().toString().trim());
                            member.setTime(textTime.getText().toString().trim());
                            member.setSymptoms(textSymptoms.getText().toString().trim());
                            member.setBloodGroup(textBloodGroup.getText().toString().trim());
                            member.setName(textName.getText().toString().trim());
                            member.setAge(textAge.getText().toString().trim());
                            member.setEmail(textEmail.getText().toString().trim());
                            member.setPhone(textPhone.getText().toString().trim());
                            member.setDoctor(textView6.getText().toString().trim());
                            member.setGender(radioSexButton.getText().toString().trim());
                            reff.push().setValue(member);
                            databaseReference.push().setValue(member);

                            String date = textDate.getText().toString().trim();
                            String time = textTime.getText().toString().trim();
                            String symptoms = textSymptoms.getText().toString().trim();
                            String blood = textBloodGroup.getText().toString().trim();
                            String name = textName.getText().toString().trim();
                            String age = textAge.getText().toString().trim();
                            String email = textEmail.getText().toString().trim();
                            String phone = textPhone.getText().toString().trim();
                            String record = textView6.getText().toString().trim();
                            String gender = radioSexButton.getText().toString().trim();

                            Intent intent = new Intent(getApplicationContext(), AppointmentBooked.class);
                            intent.putExtra("DATE",date);
                            intent.putExtra("TIME",time);
                            intent.putExtra("SYMPTOMS",symptoms);
                            intent.putExtra("BLOOD",blood);
                            intent.putExtra("NAME",name);
                            intent.putExtra("AGE",age);
                            intent.putExtra("EMAIL",email);
                            intent.putExtra("PHONE",phone);
                            intent.putExtra("DOCTOR",record);
                            intent.putExtra("GENDER", gender);
                            startActivity(intent);

                            Toast.makeText(BookAppointment.this,"Your Appointment Has Been Booked",Toast.LENGTH_SHORT).show();
                            ////////////////////////SEND SMS/////////////////////////////////////
                            String sms = textName.getText().toString()+" your appointment has been booked for "
                                    + textDate.getText().toString()+" at Time "+ textTime.getText().toString()
                                    +" with Doctor. " +textView6.getText().toString();
                            String phoneNum = textPhone.getText().toString();
                            if(!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                                if(checkPermission()) {

//Get the default SmsManager//

                                    SmsManager smsManager = SmsManager.getDefault();

//Send the SMS//

                                    smsManager.sendTextMessage(phoneNum, null, sms, null, null);
                                }else {
                                    Toast.makeText(BookAppointment.this, "Permission denied", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }

    AdapterView.OnItemSelectedListener onItemSelectedListener1 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String s1 = String.valueOf(text1[position]);
            textTime.setText(s1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(BookAppointment.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(BookAppointment.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(BookAppointment.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    button.setEnabled(false);

                }
                break;
        }
    }



}