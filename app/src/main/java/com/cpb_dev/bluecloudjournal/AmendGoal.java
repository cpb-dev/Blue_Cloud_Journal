package com.cpb_dev.bluecloudjournal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AmendGoal extends AppCompatActivity {

    TextView amendTitle, amendDesc, amendProg;
    Button btnSave, btnDelete;
    String id, title, date, desc, prog;
    SeekBar progBar;

    dbGoals goalsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_goal);

        amendTitle = findViewById(R.id.amend_goaltitle);
        amendDesc = findViewById(R.id.amend_goaldesc);
        amendProg = findViewById(R.id.amend_tv_goal_prog);

        btnSave = findViewById(R.id.btn_ag_save);
        btnDelete = findViewById(R.id.btn_ag_delete);

        progBar = (SeekBar) findViewById(R.id.amend_progbar);

        progBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                amendProg.setText("" + i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //First we get the data from the recycler view
        getIntentData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbGoals goalsDB = new dbGoals(AmendGoal.this);
                //Second the update method can be called
                String ID = getIntent().getStringExtra("id");
                String ntitle = amendTitle.getText().toString();
                String ndesc = amendDesc.getText().toString();
                String nprog = amendProg.getText().toString();

                goalsDB.updateGoals(ID, ntitle, date, ndesc, nprog);
                Toast.makeText(AmendGoal.this, "Successfully Updated!", Toast.LENGTH_LONG).show();
                onRestart(); //Method to refresh the app to show save that was made
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AmendGoal.this);
                builder.setCancelable(true);
                builder.setTitle("Delete Goal");
                builder.setMessage("Are you sure you want to delete this goal permanently?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteGoal();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsPage()).commit();
                    }
                });
                builder.show(); //Display the alert dialog
            }
        });
    }
    public void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("desc") &&
                getIntent().hasExtra("prog")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            date = getIntent().getStringExtra("date");
            desc = getIntent().getStringExtra("desc");
            prog = getIntent().getStringExtra("prog");

            amendTitle.setText(title);
            amendDesc.setText(desc);
            amendProg.setText(prog);
        } else{
            Toast.makeText(this, "No Data!!!", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteGoal(){
        goalsDb.deleteGoal(getIntent().getStringExtra("id"));
        Toast.makeText(this, "Goal Successfully Deleted!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}