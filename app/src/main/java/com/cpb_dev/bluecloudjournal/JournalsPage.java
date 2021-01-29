package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class JournalsPage extends Fragment {

    RecyclerView journalsRV;

    dbJournals journalsdb;
    ArrayList<String> journalId, journalDate, journalMood, journalWWWell, journalWWWrong;
    AdapterJournals adapterJournals;

    LinearLayout instructions;

    Button btnNewJournal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Fragment inflater
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_journals_page, null);

        btnNewJournal = (Button) v.findViewById(R.id.btn_add_new_journal);
        btnNewJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewJournal();
            }
        });

        instructions = v.findViewById(R.id.inst_card);

        journalsRV = v.findViewById(R.id.rv_journals);
        journalsdb = new dbJournals(getContext());
        journalId = new ArrayList<>();
        journalDate = new ArrayList<>();
        journalMood = new ArrayList<>();
        journalWWWell = new ArrayList<>();
        journalWWWrong = new ArrayList<>();

        displayJournals();
        adapterJournals = new AdapterJournals(getContext(), journalId, journalDate, journalMood,
                journalWWWell, journalWWWrong);
        journalsRV.setAdapter(adapterJournals);
        journalsRV.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    public void addNewJournal(){
        //Opening the AddJournal fragment on the page
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddJournal()).commit();
    }

    public void displayJournals(){
        Cursor c = journalsdb.getJournals();
        if(c.getCount() == 0){
            instructions.setVisibility(View.VISIBLE);
            journalsRV.setVisibility(View.GONE);
        } else {
            instructions.setVisibility(View.GONE);
            journalsRV.setVisibility(View.VISIBLE);

            while (c.moveToNext()){
                journalId.add(c.getString(0));
                journalDate.add(c.getString(1));
                journalMood.add(c.getString(2));
                journalWWWell.add(c.getString(3));
                journalWWWrong.add(c.getString(4));
            }
        }
    }

}