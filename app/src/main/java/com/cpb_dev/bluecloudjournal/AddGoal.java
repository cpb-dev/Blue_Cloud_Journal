package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/*
TODO: Create a database for saving the goals made by the user

TODO: Add functionality for the save and delete button
 */

public class AddGoal extends Fragment {

    public SeekBar goalProg;
    public TextView progressTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_add_goal, null);

        /*
        Implementing a seekbar with a TextView output
         */
        progressTV = (TextView) v.findViewById(R.id.tv_goal_prog);
        goalProg = (SeekBar) v.findViewById(R.id.ag_progbar);

        goalProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressTV.setText("" + i + "%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        return v;
    }
}