package com.example.tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
         return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.VehicleNo.setText(user.getVehicleNo());
        holder.OperatorName.setText(user.getOperatorName());
        holder.Starttime.setText(user.getLocation());
        holder.Endtime.setText(user.getShift());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView VehicleNo , OperatorName , Starttime, Endtime;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            VehicleNo = itemView.findViewById(R.id.textView);
            OperatorName = itemView.findViewById(R.id.textView9);
            Starttime = itemView.findViewById(R.id.editTextTime);
            Endtime = itemView.findViewById(R.id.editTextTime2);


        }

    }
}
