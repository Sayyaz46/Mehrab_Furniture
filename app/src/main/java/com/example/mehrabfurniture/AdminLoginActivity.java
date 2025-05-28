package com.example.mehrabfurniture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Button btnback = findViewById(R.id.btn_back);
        Button btnLogin = findViewById(R.id.btn_login);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, AdminMainActivity.class);
                startActivity(intent);
            }
        });



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeaveRegistrationDialog();
            }
        });

    }
    private void showLeaveRegistrationDialog() {
        new AlertDialog.Builder(AdminLoginActivity.this)  // Use RegisterCustomerActivity.this for context
                .setMessage("It will Take you to Customer Log in page!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No, Stay", null)
                .show();
    }
}