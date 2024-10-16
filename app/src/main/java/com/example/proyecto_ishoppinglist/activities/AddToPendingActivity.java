package com.example.proyecto_ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_ishoppinglist.R;
import com.example.proyecto_ishoppinglist.database.Database;
import com.example.proyecto_ishoppinglist.models.Product;

import java.util.ArrayList;

public class AddToPendingActivity extends AppCompatActivity {
    private Spinner productSpinner;
    private Button saveBtn, cancelBtn;
    private ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_pending);

        // Inicialización de vistas
        productSpinner = findViewById(R.id.spProduct);
        saveBtn = findViewById(R.id.btnSave);
        cancelBtn = findViewById(R.id.btnCancel);

        // Verificar que los productos se reciben correctamente
        productList = (ArrayList<Product>) getIntent().getSerializableExtra("productos");
        if (productList == null || productList.isEmpty()) {
            Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Llenar el spinner de productos con el nombre y el estado (pendiente o no)
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getProducts());
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(productAdapter);

        // Guardar el producto como pendiente
        saveBtn.setOnClickListener(v -> {
            int selectedPosition = productSpinner.getSelectedItemPosition();
            Product selectedProduct = productList.get(selectedPosition);

            // Cambiar el estado a pendiente
            selectedProduct.setPending(true);  // Marcar como pendiente

            // Actualizar el producto en la base de datos
            Database.updateProduct(selectedProduct);

            // Enviar el producto de vuelta a la MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedProduct", selectedProduct);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Cancelar la acción
        cancelBtn.setOnClickListener(v -> finish());
    }

    // Método para obtener los nombres de productos y su estado (pendiente o no)
    private ArrayList<String> getProducts() {
        ArrayList<String> productList = new ArrayList<>();
        for (Product product : this.productList) {
            String displayName = product.getName() + (product.isPending() ? " (Pendiente)" : " (No pendiente)") + ", " + (product.isLactose() ? "(Con lactosa)" : "(Sin lactosa)") + ", " + (product.isGluten() ? "(Con gluten)" : "(Sin gluten)");
            productList.add(displayName);
        }
        return productList;
    }
}
