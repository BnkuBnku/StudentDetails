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


public class CustomAdapt extends RecyclerView.Adapter<CustomAdapt.ViewHolder> {

    Context context;
    String[] SID;
    String[] SIN;

    public CustomAdapt(Context context, String[] SID, String[] SIN) {
        this.context = context;
        this.SID = SID;
        this.SIN = SIN;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.student_layout,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id_tv.setText(SID[position]);
        holder.student_tv.setText(SIN[position]);
    }


    @Override
    public int getItemCount() {
        return SID.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //variable for table row
        TableRow tableRow;

        //variable for textView/s
        TextView id_tv,student_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id_tv = itemView.findViewById(R.id.id_tv);
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
}
