package com.cpb_dev.bluecloudjournal;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
TODO: Add a class and relevant methods to analyse mood ratings based on time
TODO: Add a way to display a message for when there has been no data added yet
TODO: Add a function to display all activities with analysis on their most popular rating
*/

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflating the HomePage fragment
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_home, null);

        /*
        //Sample for setting visibility of the instructions on start-up
        LinearLayout instructions;
        TextView data;

        int i = 0;
        instructions = (LinearLayout)v.findViewById(R.id.inst_card);
        data = (TextView) v.findViewById(R.id.tv_data);

        if(i == 0) {
            instructions.setVisibility(View.VISIBLE);
            data.setVisibility(View.GONE);
        } else {
            instructions.setVisibility(View.GONE);
            data.setVisibility(View.VISIBLE);
        }
         */

        Button btnAddJourn = (Button)v.findViewById(R.id.btn_add_new_journal);
        btnAddJourn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewJournal();
            }
        });

        return v; //Returning the fragment view to be displayed on the screen
    }

    public void addNewJournal(){
        //Opening the AddJournal fragment on the page
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddJournal()).commit();
    }
}