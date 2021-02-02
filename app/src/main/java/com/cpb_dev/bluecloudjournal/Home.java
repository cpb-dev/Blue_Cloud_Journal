package com.cpb_dev.bluecloudjournal;

import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
TODO: Add a class and relevant methods to analyse mood ratings based on time
TODO: Add a way to display a message for when there has been no data added yet
TODO: Add a function to display all activities with analysis on their most popular rating
*/

public class Home extends Fragment {

    LinearLayout instructions, journalcard, moodcard;
    dbJournals journalsdb;
    TextView tvDate, tvWWWell, tvWWWrong;
    ImageView imgMood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflating the HomePage fragment
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_home, null);

        journalsdb = new dbJournals(getContext());

        instructions = (LinearLayout) v.findViewById(R.id.inst_card);
        moodcard = (LinearLayout) v.findViewById(R.id.hpmood_card);
        journalcard = (LinearLayout) v.findViewById(R.id.hpjournal_card);
        tvDate = (TextView) v.findViewById(R.id.hp_date);
        tvWWWell = (TextView) v.findViewById(R.id.hp_wwwell);
        tvWWWrong = (TextView) v.findViewById(R.id.hp_wwwrong);
        imgMood = (ImageView) v.findViewById(R.id.hp_imgjmood);

        displayContent();

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

    public void displayContent(){
        Cursor c = journalsdb.getJournals();
        Cursor mr = journalsdb.getLatestJournal();

        if(c.getCount() == 0) {
            instructions.setVisibility(View.VISIBLE);
            moodcard.setVisibility(View.GONE);
            journalcard.setVisibility(View.GONE);
        } else {
            instructions.setVisibility(View.GONE);
            moodcard.setVisibility(View.VISIBLE);
            journalcard.setVisibility(View.VISIBLE);

            tvDate.setText(mr.getString(1));
            tvWWWell.setText(mr.getString(3));
            tvWWWrong.setText(mr.getString(4));

            if(mr.getString(2).equals("Beam")){ //If statements to set the mood image
                imgMood.setImageResource(R.drawable.ic_smile_beam_selected);
            } else if(mr.getString(2).equals("Happy")){
                imgMood.setImageResource(R.drawable.ic_smile_selected);
            } else if(mr.getString(2).equals("Neutral")){
                imgMood.setImageResource(R.drawable.ic_neutral_selected);
            }else if(mr.getString(2).equals("Sad")){
                imgMood.setImageResource(R.drawable.ic_sad_selected);
            }else if(mr.getString(2).equals("Cry")){
                imgMood.setImageResource(R.drawable.ic_sad_cry_selected);
            } else {
                imgMood.setVisibility(View.GONE);
            }

        }
    }
}