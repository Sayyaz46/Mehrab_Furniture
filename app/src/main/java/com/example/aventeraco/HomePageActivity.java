package com.example.aventeraco;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {
    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;
    private Button buttonOpenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        listViewProducts = findViewById(R.id.list_view);
        databaseHelper = new DatabaseHelper(this);
        buttonOpenUrl = findViewById(R.id.button_open_url);

        displayProducts();
        buttonOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ViewProductsActivity
                Intent intent = new Intent(HomePageActivity.this, ViewProductsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the displayed products
        displayProducts();
    }

    private void displayProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ProductConnector adapter = new ProductConnector(this, cursor, 0);
        listViewProducts.setAdapter(adapter);
    }
}
