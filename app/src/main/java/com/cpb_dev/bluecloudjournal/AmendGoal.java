package com.cpb_dev.bluecloudjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AmendGoal extends AppCompatActivity {

    TextView amendTitle, amendDesc, amendProg;
    Button btnSave, btnDelete;
    String id, title, date, desc, prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend_goal);

        amendTitle = findViewById(R.id.amend_goaltitle);
        amendDesc = findViewById(R.id.amend_goaldesc);
        amendProg = findViewById(R.id.amend_tv_goal_prog);

        btnSave = findViewById(R.id.btn_ag_save);
        btnDelete = findViewById(R.id.btn_ag_delete);

        //First we get the data from the recycler view
        getIntentData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbGoals goalsDB = new dbGoals(AmendGoal.this);
                //Second the update method can be called
                goalsDB.updateGoals(id, title, date, desc, prog);
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

}