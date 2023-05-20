package com.example.e_clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDocuments extends AppCompatActivity {

    ListView list_View;
    DatabaseReference databaseReference;
    List<helper> uploadPDFS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_documents);
        list_View = findViewById(R.id.listView);
        uploadPDFS = new ArrayList<>();

        viewallfiles();

        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                helper helper = uploadPDFS.get(i);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(helper.getUrl()));
                startActivity(intent);
            }
        });

    }

    private void viewallfiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("UPLOADS");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    helper helper = postSnapshot.getValue(com.example.e_clinic.helper.class);
                    uploadPDFS.add(helper);
                }
                String[] uploads = new String[uploadPDFS.size()];
                for (int i=0; i<uploads.length;i++){
                    uploads[i] = uploadPDFS.get(i).getName();
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position,convertView,parent);
                        TextView myText = view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);;
                        myText.setAllCaps(true);
                        myText.setTextSize(20);
                        return view ;
                    }
                };
                list_View.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}