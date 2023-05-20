package com.example.e_clinic.Blogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.e_clinic.R;

public class BlogPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_page);
    }
    public void txt_one(View view){
        Intent intent = new Intent(BlogPage.this, Blog1.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Opening Fun Dental Facts - 1", Toast.LENGTH_SHORT).show();
    }
    public void txt_two(View view){
        Intent intent1 = new Intent(BlogPage.this, Blog2.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(), "Opening Fun Dental Facts - 2", Toast.LENGTH_SHORT).show();
    }
    public void txt_three(View view){
        Intent intent1 = new Intent(BlogPage.this, Blog3.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(), "Opening What to Eat and When", Toast.LENGTH_SHORT).show();
    }
    public void txt_four(View view){
        Intent intent1 = new Intent(BlogPage.this, Blog4.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(), "Opening International Yoga Day 2019", Toast.LENGTH_SHORT).show();
    }
    public void txt_five(View view){
        Intent intent1 = new Intent(BlogPage.this, Blog5.class);
        startActivity(intent1);
        Toast.makeText(getApplicationContext(), "Opening Teeth Whitening", Toast.LENGTH_SHORT).show();
    }

}