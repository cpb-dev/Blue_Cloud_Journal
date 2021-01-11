package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/*
TODO: Create a database for saving the goals made by the user

TODO: Add functionality for the save and delete button
 */

public class AddGoal extends Fragment {

    public SeekBar goalProg;
    public TextView progressTV;
    public Button btnSave, btnDelete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_add_goal, null);

        btnDelete = (Button) v.findViewById(R.id.btn_ag_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGoal();
            }
        });

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

    public void deleteGoal(){
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getContext());
        deleteDialog.setTitle("Delete Goal");
        deleteDialog.setMessage("Are you sure you want to delete?");
        deleteDialog.setCancelable(true);

        deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Deleted Goal!", Toast.LENGTH_LONG).show();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsPage()).commit();
            }
        });

        deleteDialog.setNegativeButton("No", null);

        deleteDialog.show();
    }

}