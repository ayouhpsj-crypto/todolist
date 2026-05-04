package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText username, password;
    private Button loginbtn, registerbtn;
    private DatabaseHelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.registerbtn);

        databasehelper = new DatabaseHelper(this);

        loginbtn.setOnClickListener(v -> {

            String userText = username.getText() != null ? username.getText().toString().trim() : "";
            String passText = password.getText() != null ? password.getText().toString().trim() : "";

            if (userText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {

                if (databasehelper.loginAccount(userText, passText, MainActivity.this)) {
                    Intent intent = new Intent(MainActivity.this, todolist.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerbtn.setOnClickListener(v -> {

            String userText = username.getText() != null ? username.getText().toString().trim() : "";
            String passText = password.getText() != null ? password.getText().toString().trim() : "";

            if (userText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {

                databasehelper.createAccount(userText, passText, MainActivity.this);

                Intent intent = new Intent(MainActivity.this, todolist.class);
                startActivity(intent);
                finish();
            }
        });
    }
}