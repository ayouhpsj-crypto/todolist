
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

    EditText taskinput;
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
        taskinput = findViewById(R.id.taskInput);
        addBtn = findViewById(R.id.addBtn);
        listview = findViewById(R.id.listView);

        taskList = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                taskList
        );

        listview.setAdapter(adapter);


        addBtn.setOnClickListener(v -> {
            String task = taskinput.getText().toString();
            if (!task.isEmpty()) {
                if (selectedPosition == -1) {

                    taskList.add(task);
                } else {
                    // تعديل المهمة المحددة
                    taskList.set(selectedPosition, task);
                    selectedPosition = -1;
                }
                adapter.notifyDataSetChanged();
                taskinput.setText("");
            }
        });


        listview.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTask = taskList.get(position);
            taskinput.setText(selectedTask);
            selectedPosition = position;
        });

        listview.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}