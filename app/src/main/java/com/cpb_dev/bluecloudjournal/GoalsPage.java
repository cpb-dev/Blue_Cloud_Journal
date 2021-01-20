package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/*
TODO: Add a default message for when there are no goals(when app is first installed)
TODO: Add a view for displaying the user's goals, gathered from the goals database
TODO: Add a method of deleting the goals from the database if requested

Done: Use the addNewGoal() function to direct the user to a new fragment
 */

public class GoalsPage extends Fragment {

    RecyclerView goalsRV;

    dbGoals goalsdb;
    ArrayList<String> goalId, goalTitle, goalDesc, goalDate, goalProg;
    AdapterGoals adapterGoals;

    LinearLayout instructions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Layout inflater for goals_page fragment
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_goals_page, null);

        Button btnAddGoal = (Button) v.findViewById(R.id.btn_add_new_goal);

        btnAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewGoal();
            }
        });

        instructions = (LinearLayout)v.findViewById(R.id.inst_card);

        goalsRV = v.findViewById(R.id.rv_goals);
        goalsdb = new dbGoals(getContext());
        goalId = new ArrayList<>();
        goalTitle = new ArrayList<>();
        goalDesc = new ArrayList<>();
        goalDate = new ArrayList<>();
        goalProg = new ArrayList<>();

        displayGoals();
        adapterGoals = new AdapterGoals(getContext(), goalId, goalTitle, goalDate, goalDesc, goalProg);
        goalsRV.setAdapter(adapterGoals);
        goalsRV.setLayoutManager(new LinearLayoutManager(getContext()));

        return v; //Return inflater
    }

    public void addNewGoal() {
        //Opening the new goal fragment in place of current fragment
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddGoal()).commit();
    }

    public void displayGoals(){
        Cursor c = goalsdb.getGoals();
        if(c.getCount() == 0){
            instructions.setVisibility(View.VISIBLE);
            goalsRV.setVisibility(View.GONE);
            //TODO: Put visibility gone for the Recycler View and display instructions
        } else {
            //TODO: Put visibility gone for instructions and display Recycler View
            instructions.setVisibility(View.GONE);
            goalsRV.setVisibility(View.VISIBLE);

            while (c.moveToNext()){
                goalId.add(c.getString(0));
                goalTitle.add(c.getString(1));
                goalDate.add(c.getString(2));
                goalDesc.add(c.getString(3));
                goalProg.add(c.getString(4));
            }
        }
    }
}