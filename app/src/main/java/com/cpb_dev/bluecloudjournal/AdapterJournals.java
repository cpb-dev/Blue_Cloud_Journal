package com.cpb_dev.bluecloudjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterJournals extends RecyclerView.Adapter<AdapterJournals.MyViewHolder> {

    private Context context;
    private ArrayList journalID, journalDate, journalMood, journalWWWell, journalWWWrong;

    AdapterJournals(Context context, ArrayList journalId, ArrayList journalDate, ArrayList journalMood,
                    ArrayList journalWWWell, ArrayList journalWWWrong){

        this.context = context;
        this.journalID = journalId;
        this.journalDate = journalDate;
        this.journalMood = journalMood;
        this.journalWWWell = journalWWWell;
        this.journalWWWrong = journalWWWrong;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_journal, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_journalId.setText(String.valueOf(journalID.get(position)));
        holder.txt_journalDate.setText(String.valueOf(journalDate.get(position)));
        holder.txt_journalMood.setText(String.valueOf(journalMood.get(position)));
        holder.txt_journalWWWell.setText(String.valueOf(journalWWWell.get(position)));
        holder.txt_journalWWWrong.setText(String.valueOf(journalWWWrong.get(position)));
    }

    @Override
    public int getItemCount() {
        return journalID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_journalId, txt_journalDate, txt_journalMood, txt_journalWWWell, txt_journalWWWrong;
        LinearLayout journalsLayout;

        //TODO: Sort out the ImageView for the mood

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            txt_journalId = itemView.findViewById(R.id.list_jid);
            txt_journalDate = itemView.findViewById(R.id.list_jdate);
            txt_journalMood = itemView.findViewById(R.id.list_jmood);
            txt_journalWWWell = itemView.findViewById(R.id.list_jwwwell);
            txt_journalWWWrong = itemView.findViewById(R.id.list_jwwwrong);
            journalsLayout = itemView.findViewById(R.id.journalsLayout);
        }
    }
}
