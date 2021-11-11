package com.example.mystudents;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.StudentIDViewHolder> {
    Context context;
    String[] SID;
    String[] SIN;

    public MainAdapter(Context context, String[] SID, String[] SIN) {
        this.context = context;
        this.SID = SID;
        this.SIN = SIN;
    }

    public class StudentIDViewHolder extends ViewHolder{
        //variable for table row
        TableRow tableRow;

        //variable for textView/s
        TextView id_tv,student_tv, idcheck;

        String[] id = new String[1];

        public StudentIDViewHolder(@NonNull View itemView) {
            super(itemView);

            id_tv = itemView.findViewById(R.id.id_tv);
            idcheck = itemView.findViewById(R.id.editTextTextPersonName);
            student_tv = itemView.findViewById(R.id.student_tv);
            tableRow = itemView.findViewById(R.id.student_details);
            tableRow.setOnClickListener(onRowClick);

        }

        View.OnClickListener onRowClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, StudentEnrolledActivity.class);
                i.putExtra("ID", id_tv.getText().toString());
                i.putExtra("Name", student_tv.getText().toString());

                context.startActivity(i);
            }
        };

    }


    @Override
    public int getItemCount() {
        return SID.length;
    }


    @NonNull
    @Override
    public StudentIDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.student_layout,parent, false);
        return new StudentIDViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentIDViewHolder holder, int position) {
        holder.id_tv.setText(SID[position]);
        holder.student_tv.setText(SIN[position]);
    }
}
