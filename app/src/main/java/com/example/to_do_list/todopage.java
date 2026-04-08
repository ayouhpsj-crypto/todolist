package com.example.to_do_list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class todopage extends AppCompatActivity {

    private EditText taskInput;
    private Button addBtn;
    private ListView listView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todopage);

        taskInput = findViewById(R.id.taskInput);
        addBtn = findViewById(R.id.addBtn);
        listView = findViewById(R.id.listView);

        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                R.layout.item_task,
                R.id.taskText,
                taskList
        );
        listView.setAdapter(adapter);


        addBtn.setOnClickListener(v -> {
            String task = taskInput.getText().toString().trim();
            if (!task.isEmpty()) {
                if (selectedPosition == -1) {

                    taskList.add(task);
                } else {

                    taskList.set(selectedPosition, task);
                    selectedPosition = -1;
                }
                adapter.notifyDataSetChanged();
                taskInput.setText("");
            }
        });





    }
}