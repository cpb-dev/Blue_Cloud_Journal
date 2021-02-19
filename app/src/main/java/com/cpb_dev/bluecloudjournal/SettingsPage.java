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
import android.widget.Button;
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

    dbGoals goalsDB;
    dbJournals journalsDB;

    ToggleButton tbDaily;
    Button wipeAll, addNew;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.activity_settings_page, null);

        goalsDB = new dbGoals(getContext());
        journalsDB = new dbJournals(getContext());

        wipeAll = v.findViewById(R.id.test_wipe);
        addNew = v.findViewById(R.id.test_add);

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

        /* These buttons are only needed for testing purposes */
        wipeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testWipe();
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testData();
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

    /* The test methods are to be removed before final product */
    public void testWipe(){
        goalsDB.wipeAll();
        journalsDB.wipeAll();
    }

    public void testData(){
        goalsDB.addGoal("test 1", "20/01/2021",
                "Basic description 1", "20%");
        goalsDB.addGoal("test 2", "21/01/2021",
                "Basic description 2, this is a bit longer!", "40%");
        goalsDB.addGoal("test 3", "22/01/2021",
                "Basic description 3", "55%");
        goalsDB.addGoal("test 4", "23/01/2021",
                "Basic description 4", "80%");
        goalsDB.addGoal("test 5", "24/01/2021",
                "Basic description 5", "10%");

        journalsDB.addJournal("20/01/2021", "Happy",
                "What went well for this journal 1!",
                "What went wrong for this journal 1!");
        journalsDB.addJournal("21/01/2021", "Sad",
                "What went well for this journal 2!",
                "What went wrong for this journal 2!");
        journalsDB.addJournal("22/01/2021", "Neutral",
                "What went well for this journal 3!",
                "What went wrong for this journal 3!");
        journalsDB.addJournal("23/01/2021", "Cry",
                "What went well for this journal 4!",
                "What went wrong for this journal 4!");
        journalsDB.addJournal("24/01/2021", "Beam",
                "What went well for this journal 5!",
                "What went wrong for this journal 5!");
    }

}