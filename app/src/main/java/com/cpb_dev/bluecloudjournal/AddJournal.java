package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
TODO: Make rating system, so the emoji buttons provide a rating output
TODO: Make a list of basic activities for the user to choose from
TODO: Include a method for users to add activities of their own
TODO: Make the view for the activities added to be invisible until an activity is inputted
 */

public class AddJournal extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Fragment inflater
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_add_journal, null);

        String cDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        TextView date = (TextView) v.findViewById(R.id.date);
        date.setText(cDate);

        return v; //Returning the fragment
    }
}