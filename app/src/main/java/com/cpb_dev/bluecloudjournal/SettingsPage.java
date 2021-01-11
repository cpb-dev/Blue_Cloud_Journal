package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
TODO: Add a toggle button for notifications
TODO: Add an option for notification frequency
TODO: Add a method to send the user a notification reminder for creating a new journal entry
 */

public class SettingsPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_settings_page, null);

        return v;
    }
}