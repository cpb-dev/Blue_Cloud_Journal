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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
TODO: Make rating system, so the emoji buttons provide a rating output

TODO: Make a list of basic activities for the user to choose from (SQLite Database)

TODO: Include a method for users to add activities of their own
Do this through having a pop up whereby the user can add an activity to their day.
A spinner will be on the pop up with a list of already made activities, which the user can add to.

TODO: Make the view for the activities added to be invisible until an activity is inputted
 */

public class AddJournal extends Fragment {

    String cDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Fragment inflater
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_add_journal, null);

        /* Making the date TextView display the current date */
        cDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        TextView date = (TextView) v.findViewById(R.id.date);
        date.setText(cDate);

        /* Generating the buttons and their functions */

        return v; //Returning the fragment
    }

}