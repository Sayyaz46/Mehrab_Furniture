package com.example.mehrabfurniture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText etProductId, etProductName, etProductPrice, etProductQuantity;
    private Button btnUpdateProduct;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        etProductId = findViewById(R.id.et_product_id);
        etProductName = findViewById(R.id.et_product_name);
        etProductPrice = findViewById(R.id.et_product_price);
        etProductQuantity = findViewById(R.id.et_product_quantity);
        btnUpdateProduct = findViewById(R.id.btn_update_product);
        dbHelper = new DatabaseHelper(this);

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int productId = Integer.parseInt(etProductId.getText().toString());
                String name = etProductName.getText().toString();
                double price = Double.parseDouble(etProductPrice.getText().toString());
                int quantity = Integer.parseInt(etProductQuantity.getText().toString());

                boolean result = dbHelper.updateProduct(productId, name, price, quantity);

                if (result) {
                    Toast.makeText(UpdateProductActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateProductActivity.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
