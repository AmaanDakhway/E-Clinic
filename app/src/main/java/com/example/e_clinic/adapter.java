package com.example.e_clinic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter extends FirebaseRecyclerAdapter<Checkmodel, adapter.myviewholder> {

    public adapter(@NonNull FirebaseRecyclerOptions<Checkmodel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Checkmodel Checkmodel) {
        holder.textView.setText(Checkmodel.getName());
        holder.textView2.setText(Checkmodel.getPhone());
        holder.textView3.setText(Checkmodel.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper1,new CheckDescFragment(
                        Checkmodel.getAge(), Checkmodel.getBloodGroup(),
                        Checkmodel.getDate(),Checkmodel.getDoctor(),
                        Checkmodel.getEmail(),Checkmodel.getGender(),
                        Checkmodel.getName(),Checkmodel.getPhone(),
                        Checkmodel.getSymptoms(),Checkmodel.getTime())).addToBackStack(null).commit();

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView textView,textView2,textView3;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);


        }
    }

}