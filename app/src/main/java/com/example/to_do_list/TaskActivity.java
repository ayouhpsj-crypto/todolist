package com.example.to_do_list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {
    // EditText taskinput; wont be used any more
    Button addBtn;
    ListView listview;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        // find the input from the creates a new task window
        EditText Tname = findViewById(R.id.taskName);
        EditText Ttype = findViewById(R.id.taskType); // not used for now
        EditText Tdate = findViewById(R.id.taskDate); // not uesd for now
        EditText Ttime = findViewById(R.id.taskTime); // not used for now

        // find the save button
        Button save = findViewById(R.id.saveButton);


        // after clicking save
        save.setOnClickListener(W -> {
            String name = Tname.getText().toString();
            if (!name.isEmpty()) {
                // add the new task to the list with it's name
                taskList.add(name);
                adapter.notifyDataSetChanged();

                // exit the create window
            }
        });

    }
}