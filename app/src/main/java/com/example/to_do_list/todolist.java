package com.example.to_do_list;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class todolist extends AppCompatActivity {

    private EditText taskInput;
    private Button addBtn, editBtn, deleteBtn;
    private ListView listView;

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;

    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todolist);

        taskInput = findViewById(R.id.taskInput);
        addBtn = findViewById(R.id.addBtn);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        listView = findViewById(R.id.listView);

        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                taskList
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            taskInput.setText(taskList.get(position));
        });

        addBtn.setOnClickListener(v -> {
            String task = taskInput.getText().toString().trim();

            if (!task.isEmpty()) {
                taskList.add(task);
                adapter.notifyDataSetChanged();
                taskInput.setText("");
            }
        });

        editBtn.setOnClickListener(v -> {

            if (selectedPosition != -1) {

                String updatedTask = taskInput.getText().toString().trim();

                if (!updatedTask.isEmpty()) {
                    taskList.set(selectedPosition, updatedTask);
                    adapter.notifyDataSetChanged();
                    taskInput.setText("");
                    selectedPosition = -1;
                }

            } else {
                Toast.makeText(this, "Select a task first", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(v -> {

            if (selectedPosition != -1) {

                taskList.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                taskInput.setText("");
                selectedPosition = -1;

            } else {
                Toast.makeText(this, "Select a task first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}