package com.example.mehrabfurniture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMainActivity extends AppCompatActivity {

    private Button btnAddProduct, btnUpdateProduct, btnDeleteProduct, btnViewProduct, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        btnAddProduct = findViewById(R.id.btn_add_product);
        btnUpdateProduct = findViewById(R.id.btn_update_product);
        btnDeleteProduct = findViewById(R.id.btn_delete_product);
        btnViewProduct = findViewById(R.id.btn_view_product);
        btnLogout = findViewById(R.id.btn_logout);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, AddProductActivity.class));
            }
        });

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, UpdateProductActivity.class));
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, DeleteProductActivity.class));
            }
        });

        btnViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, ViewProductsActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, MainActivity.class));
            }
        });
    }
}
