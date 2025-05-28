package com.example.mehrabfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private TextView admRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(MainActivity.this);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        admRegister = findViewById(R.id.admin_login);

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterCustomerActivity.class);
            startActivity(intent);
        });

        admRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterCustomerActivity.class); // Change to AdminLoginActivity or appropriate activity
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (username.equals("admin") && password.equals("admin")) {
                Toast.makeText(MainActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AdminMainActivity.class);
                startActivity(intent);

            } else {
                boolean result = dbHelper.checkLogin(username, password);

                if (result) {
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

                }
                });
        }
    }