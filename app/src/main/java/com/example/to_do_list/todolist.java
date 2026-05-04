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
import java.util.List;

public class todolist extends AppCompatActivity {

    private EditText taskInput;
    private Button addBtn, editBtn, deleteBtn;
    private Button addImageBtn, addTextBtn, addAudioBtn;
    private ListView listView;

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private DatabaseList databaseList;

    private int selectedPosition = -1;

    private void loadTasks() {
        taskList.clear();
        List<Task> tasks = databaseList.getAllTasks();
        for (Task task : tasks) {
            taskList.add(task.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todolist);

        listView = findViewById(R.id.listView);
        databaseList = new DatabaseList(this);
        taskList = new ArrayList<>();
        taskInput = findViewById(R.id.taskInput);

        addBtn = findViewById(R.id.addBtn);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        addImageBtn = findViewById(R.id.addImageBtn);
        addTextBtn = findViewById(R.id.addTextBtn);
        addAudioBtn = findViewById(R.id.addAudioBtn);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                taskList
        );

        listView.setAdapter(adapter);
        loadTasks();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            taskInput.setText(taskList.get(position));
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            String task = taskList.get(position);

            if (task.startsWith("✔ ")) {
                task = task.replace("✔ ", "");
            } else {
                task = "✔ " + task;
            }

            Task dbTask = databaseList.getAllTasks().get(position);
            databaseList.updateTask(dbTask.getId(), task);

            loadTasks();
            adapter.notifyDataSetChanged();

            return true;
        });

        addBtn.setOnClickListener(v -> {
            String task = taskInput.getText().toString().trim();

            if (!task.isEmpty()) {
                databaseList.insertData(task);
                loadTasks();
                adapter.notifyDataSetChanged();
                taskInput.setText("");
            }
        });

        editBtn.setOnClickListener(v -> {
            loadTasks();
            adapter.notifyDataSetChanged();
        });

        deleteBtn.setOnClickListener(v -> {

            if (selectedPosition != -1) {

                Task task = databaseList.getAllTasks().get(selectedPosition);
                databaseList.deleteTask(task);
                loadTasks();
                adapter.notifyDataSetChanged();
                taskInput.setText("");
                selectedPosition = -1;

            } else {
                Toast.makeText(this, "Select a task first", Toast.LENGTH_SHORT).show();
            }
        });

        addTextBtn.setOnClickListener(v -> {
            String task = taskInput.getText().toString().trim();

            if (!task.isEmpty()) {
                databaseList.insertData(task);
                loadTasks();
                adapter.notifyDataSetChanged();
                taskInput.setText("");
            }
        });

        addImageBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Add Image clicked (later)", Toast.LENGTH_SHORT).show();
        });

        addAudioBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Add Audio clicked (later)", Toast.LENGTH_SHORT).show();
        });
    }
}