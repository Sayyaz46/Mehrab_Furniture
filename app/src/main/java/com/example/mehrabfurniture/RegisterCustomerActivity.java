package com.example.mehrabfurniture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class RegisterCustomerActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etMobile;
    private EditText etAddress;
    private Button btnRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);

        etUsername = findViewById(R.id.et_register_username);
        etEmail = findViewById(R.id.et_register_email);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        etMobile = findViewById(R.id.et_register_mobile);
        etAddress = findViewById(R.id.et_register_address);
        btnRegister = findViewById(R.id.btn_register_r);
        btnLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                if (validateInputs(username, email, password, confirmPassword, mobile, address)) {
                    DatabaseHelper dbHelper = new DatabaseHelper(RegisterCustomerActivity.this);
                    boolean isInserted = dbHelper.insertUser(username, email, password, mobile, address);

                    if (isInserted) {
                        Toast.makeText(RegisterCustomerActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterCustomerActivity.this, com.example.mehrabfurniture.MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterCustomerActivity.this, "Registration Unsuccessful :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeaveRegistrationDialog();
            }
        });
    }

    private boolean validateInputs(String username, String email, String password, String confirmPassword, String mobile, String address) {
        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        }

        if (confirmPassword.isEmpty()) {
            etConfirmPassword.setError("Please confirm your password");
            etConfirmPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        if (mobile.isEmpty()) {
            etMobile.setError("Mobile number is required");
            etMobile.requestFocus();
            return false;
        }

        if (address.isEmpty()) {
            etAddress.setError("Address is required");
            etAddress.requestFocus();
            return false;
        }

        return true;
    }

    private void showLeaveRegistrationDialog() {
        new AlertDialog.Builder(RegisterCustomerActivity.this)
                .setMessage("Are you sure you want to leave the registration page?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RegisterCustomerActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
