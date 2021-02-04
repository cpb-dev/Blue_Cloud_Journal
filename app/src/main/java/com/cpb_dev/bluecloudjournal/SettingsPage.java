package com.cpb_dev.bluecloudjournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/*
DONE: Add a toggle button for notifications
DONE: Add a method to send the user a notification reminder for creating a new journal entry
 */

public class SettingsPage extends Fragment {

    ToggleButton tbDaily;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_settings_page, null);

        tbDaily = (ToggleButton) v.findViewById(R.id.tb_dailyrem);

        tbDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton ButtonView, boolean isChecked) {
                if(isChecked){
                    alarm();
                } else {
                    //No action needs to be made
                    Toast.makeText(getContext(), "No Reminder Set!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    public void alarm(){
        Intent intent = new Intent(getContext(), NotifyService.class);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(getContext(), 0, intent, 0);

        Calendar calendar = Calendar.getInstance(); //Set time to 9am
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1); //Default is 1 (doesn't matter as it's daily)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 60 * 24, pendingIntent); //Milisecs are how many in a day (as this is daily)

        Toast.makeText(getContext(), "Daily Reminder Set!", Toast.LENGTH_LONG).show();
    }

}