package com.example.mehrabfurniture;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewProductsActivity extends AppCompatActivity {
    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        listViewProducts = findViewById(R.id.list_view);
        databaseHelper = new DatabaseHelper(this);

        displayProducts();


    }
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the displayed products
        displayProducts();
    }

    private void displayProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ProductAdapter adapter = new ProductAdapter(this, cursor, 0);
        listViewProducts.setAdapter(adapter);
    }


}