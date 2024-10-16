package com.example.proyecto_ishoppinglist.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_ishoppinglist.R;
import com.example.proyecto_ishoppinglist.database.Database;
import com.example.proyecto_ishoppinglist.models.Product;

public class AddProductActivity extends AppCompatActivity {
    private EditText productNameEditText, productDetailsEditText;
    private Button saveBtn, cancelBtn;
    private Switch swLactose, swGluten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Inicialización de vistas
        productNameEditText = findViewById(R.id.productNameEditText);
        productDetailsEditText = findViewById(R.id.productDetailsEditText);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        swLactose = findViewById(R.id.swLactose);
        swGluten = findViewById(R.id.swGluten);


        // Botón para guardar el producto nuevo
        saveBtn.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString().trim();
            String productDetails = productDetailsEditText.getText().toString().trim();
            boolean lactose = swLactose.isChecked();
            boolean gluten = swGluten.isChecked();

            if (!productName.isEmpty() && !productDetails.isEmpty()) {
                Product newProduct = new Product(productName, productDetails, false, lactose, gluten);
                Database.addProduct(newProduct);
                Toast.makeText(AddProductActivity.this, "Producto añadido", Toast.LENGTH_SHORT).show();
                finish();  // Vuelve a la actividad principal
            } else {
                Toast.makeText(AddProductActivity.this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para cancelar y volver a la actividad principal
        cancelBtn.setOnClickListener(v -> finish());
    }
}
