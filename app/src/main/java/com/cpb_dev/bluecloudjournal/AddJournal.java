package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
Done: Make rating system, so the emoji buttons provide a rating output
TODO: Simplify the mood ratings (change from the current extended if statements)

TODO: Add a database for the journals to be saved
TODO: Have necessary input validation for required user inputs
TODO: Include a way to delete/cancel a journal entry
TODO: Save all the user inputs into journal database

Done: Make a list of basic activities for the user to choose from (SQLite Database)

Discarded: Include a method for users to add activities of their own
Do this through having a pop up whereby the user can add an activity to their day.
A spinner will be on the pop up with a list of already made activities, which the user can add to.

Discarded: Make the view for the activities added to be invisible until an activity is inputted
 */

public class AddJournal extends Fragment {

    String cDate;
    Button btnSave, btnDelete;
    RadioButton rbHappyBeam, rbHappy, rbNeutral, rbSad, rbSadCry;
    RadioGroup rgMood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Fragment inflater
        final View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_add_journal, null);

        /* Making the date TextView display the current date */
        cDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        TextView date = (TextView) v.findViewById(R.id.date);
        date.setText(cDate);

        /* Generating mood radio buttons */
        rgMood = (RadioGroup)v.findViewById(R.id.rg_mood);
        rbHappyBeam = (RadioButton)v.findViewById(R.id.rbtn_sbeam);
        rbHappy = (RadioButton)v.findViewById(R.id.rbtn_smile);
        rbNeutral = (RadioButton)v.findViewById(R.id.rbtn_neutral);
        rbSad = (RadioButton)v.findViewById(R.id.rbtn_sad);
        rbSadCry = (RadioButton)v.findViewById(R.id.rbtn_cry);

        /* Generating the buttons and their functions */
        btnSave = (Button)v.findViewById(R.id.btn_aj_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rgMood.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getContext(), "Please select a mood!", Toast.LENGTH_LONG).show();
                } else {
                    if(rbHappyBeam.isChecked()){
                        Toast.makeText(getContext(), "Happy Beam is selected", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Other is selected", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnDelete = (Button)v.findViewById(R.id.btn_aj_delete);

        return v; //Returning the fragment
    }

}