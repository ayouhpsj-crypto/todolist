package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextInputEditText username = findViewById(R.id.username);
        TextInputEditText password = findViewById(R.id.password);
        Button loginbtn = findViewById(R.id.loginbtn);
        Button registerbtn = findViewById(R.id.registerbtn);

        loginbtn.setOnClickListener(v -> {

            String userText = username.getText().toString();
            String passText = password.getText().toString();


            if (userText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else{
                DatabaseHelper databasehelper = new DatabaseHelper(MainActivity.this);

                if (databasehelper.loginAccount(userText,passText,MainActivity.this)) {

                    Intent intent = new Intent(MainActivity.this, todolist.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        registerbtn.setOnClickListener(v -> {
            String userText = username.getText().toString();
            String passText = password.getText().toString();

            if (userText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

            } else {
                DatabaseHelper databasehelper = new DatabaseHelper(MainActivity.this);

                databasehelper.createAccount(userText, passText, MainActivity.this);

                Intent intent = new Intent(MainActivity.this, todolist.class);
                startActivity(intent);
                finish();
            }
        });
    }
}