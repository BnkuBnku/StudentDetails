package com.example.mystudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EnrolledAdapter extends RecyclerView.Adapter<EnrolledAdapter.StudentEnrolledViewHolder> {
    Context context;

    String[] D_Off;
    String[] D_Sub;
    String[] D_Sched;
    String[] D_Room;
    String[] D_Teach;

    public EnrolledAdapter(Context context, String[] d_Off, String[] d_Sub, String[] d_Sched, String[] d_Room, String[] d_Teach) {
        this.context = context;
        D_Off = d_Off;
        D_Sub = d_Sub;
        D_Sched = d_Sched;
        D_Room = d_Room;
        D_Teach = d_Teach;
    }

    public class StudentEnrolledViewHolder extends RecyclerView.ViewHolder {
        TextView off_tv, sub_tv, sched_tv, room_tv, teach_tv;

        public StudentEnrolledViewHolder(@NonNull View itemView) {
            super(itemView);
            off_tv = itemView.findViewById(R.id.off_tv_in);
            sub_tv = itemView.findViewById(R.id.sub_tv_in);
            sched_tv = itemView.findViewById(R.id.sched_tv_in);
            room_tv = itemView.findViewById(R.id.room_tv_in);
            teach_tv = itemView.findViewById(R.id.teach_tv_in);
        }
    }

    @Override
    public int getItemCount() {
        return D_Off.length;
    }

    @NonNull
    @Override
    public StudentEnrolledViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.student_enrolled_layout,parent, false);
        return new StudentEnrolledViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentEnrolledViewHolder holder, int position) {
        holder.off_tv.setText(D_Off[position]);
        holder.sub_tv.setText(D_Sub[position]);
        holder.sched_tv.setText(D_Sched[position]);
        holder.room_tv.setText(D_Room[position]);
        holder.teach_tv.setText(D_Teach[position]);
    }




}
