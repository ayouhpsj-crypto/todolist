package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private final String CORRECT_USERNAME = "admin";
    private final String CORRECT_PASSWORD = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextInputEditText username = findViewById(R.id.username);
        TextInputEditText password = findViewById(R.id.password);
        Button loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(v -> {
            String userText = username.getText().toString();
            String passText = password.getText().toString();

            if (userText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (userText.equals(CORRECT_USERNAME) && passText.equals(CORRECT_PASSWORD)) {
                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, todopage.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Username or Password incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}