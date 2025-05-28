package com.example.mehrabfurniture;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteProductActivity extends AppCompatActivity {

    private EditText etProductId;
    private Button btnDeleteProduct;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        etProductId = findViewById(R.id.et_product_id);
        btnDeleteProduct = findViewById(R.id.btn_delete_product);
        dbHelper = new DatabaseHelper(this);

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int productId = Integer.parseInt(etProductId.getText().toString());
                boolean result = dbHelper.deleteProduct(productId);

                if (result) {
                    Toast.makeText(DeleteProductActivity.this, "Product Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteProductActivity.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
