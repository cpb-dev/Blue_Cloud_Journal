package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/*
TODO: Add a default message for when there are no goals(when app is first installed
TODO: Use the addNewGoal() function to direct the user to a new fragment
 */

public class GoalsPage extends Fragment {

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

        return v; //Return inflater
    }

    public void addNewGoal() {
        //Opening the new goal fragment in place of current fragment
        Toast.makeText(getActivity(), "Unavailable till page is created!", Toast.LENGTH_LONG).show();
    }
}