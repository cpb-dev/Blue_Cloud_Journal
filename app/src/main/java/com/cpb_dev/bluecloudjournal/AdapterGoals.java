package com.cpb_dev.bluecloudjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterGoals extends RecyclerView.Adapter<AdapterGoals.MyViewHolder> {

    private Context context;
    private ArrayList goalId, goalTitle, goalDate, goalDesc, goalProg;

    AdapterGoals(Context context, ArrayList goalId, ArrayList goalTitle, ArrayList goalDate,
                 ArrayList goalDesc, ArrayList goalProg){

        this.context = context;
        this.goalId = goalId;
        this.goalTitle = goalTitle;
        this.goalDate = goalDate;
        this.goalDesc = goalDesc;
        this.goalProg = goalProg;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_goal, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_goalId.setText(String.valueOf(goalId.get(position)));
        holder.txt_goalTitle.setText(String.valueOf(goalTitle.get(position)));
        holder.txt_goalDate.setText(String.valueOf(goalDate.get(position)));
        holder.txt_goalDesc.setText(String.valueOf(goalDesc.get(position)));
        holder.txt_goalProg.setText(String.valueOf(goalProg.get(position)));
    }

    @Override
    public int getItemCount() {
        return goalId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_goalId, txt_goalTitle, txt_goalDate, txt_goalDesc, txt_goalProg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_goalId = itemView.findViewById(R.id.list_gid);
            txt_goalTitle = itemView.findViewById(R.id.list_gtitle);
            txt_goalDate = itemView.findViewById(R.id.list_gdate);
            txt_goalDesc = itemView.findViewById(R.id.list_gdesc);
            txt_goalProg = itemView.findViewById(R.id.list_gprog);
        }
    }
}
