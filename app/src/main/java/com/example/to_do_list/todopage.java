
package com.example.to_do_list;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class todopage extends AppCompatActivity {

    // EditText taskinput; wont be used anymore
    Button addBtn;
    ListView listview;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todopage);

        // ربط العناصر بالـ XML
        // taskinput = findViewById(R.id.taskInput); // wont me used anymore
        addBtn = findViewById(R.id.addBtn); // the add task button
        listview = findViewById(R.id.listView); // the list of tasks

        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                taskList
        );

        listview.setAdapter(adapter);


        addBtn.setOnClickListener(v -> {

            // inflate the creates a new task file
            View task = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

            // find the input from the creates a new task window
            EditText Tname = task.findViewById(R.id.taskName);
            EditText Ttype = task.findViewById(R.id.taskType);
            EditText Tdate = task.findViewById(R.id.taskDate);
            EditText Ttime = task.findViewById(R.id.taskTime);

            // define the save button
            Button save = task.findViewById(R.id.saveButton);

            AlertDialog popUp = new AlertDialog.Builder(this).setView(task).create();

            // after clicking save
            save.setOnClickListener(W -> {
                String name = Tname.getText().toString();
                String type = Ttype.getText().toString();
                String time = Ttime.getText().toString();
                String date = Tdate.getText().toString();

                if (!name.isEmpty() && !type.isEmpty() && !time.isEmpty() && !date.isEmpty()) {
                    // add the new task to the list with its name
                    taskList.add((name + " (" +type+ ") " +date+ " at " +time));
                    adapter.notifyDataSetChanged();

                    // exit the create window
                    popUp.dismiss();
                } else if (!name.isEmpty()) {
                    taskList.add(name);
                    adapter.notifyDataSetChanged();

                    // exit the create window
                    popUp.dismiss();
                }
            });
            // makes the old window appear angain
            popUp.show();
        });

        /*

        listview.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTask = taskList.get(position);
            taskinput.setText(selectedTask);
            selectedPosition = position;
        });
        */

        listview.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}