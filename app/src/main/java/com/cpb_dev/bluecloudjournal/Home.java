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

public class Home extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflating the HomePage fragment
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_home, null);

        Button btnAddJourn = (Button)v.findViewById(R.id.btnAddNewJourn);
        btnAddJourn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewJournal();
            }
        });

        return v; //Returning the fragment view
    }

    public void addNewJournal(){
        //Opening the AddJournal fragment on the page
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddJournal()).commit();
    }
}