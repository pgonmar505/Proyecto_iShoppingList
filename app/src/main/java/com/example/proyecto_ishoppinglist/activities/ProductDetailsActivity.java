package com.example.proyecto_ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_ishoppinglist.R;
import com.example.proyecto_ishoppinglist.models.Product;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView productNameTextView, productDetailsTextView;
    private Button editBtn;
    private Button backBtn;
    private Product product;
    private Switch swLactose, swGluten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productNameTextView = findViewById(R.id.productNameTextView);
        productDetailsTextView = findViewById(R.id.productDetailsTextView);
        editBtn = findViewById(R.id.editBtn);
        backBtn = findViewById(R.id.backBtn); // Inicializar el botón "Volver"
        swLactose = findViewById(R.id.swLactose);
        swGluten = findViewById(R.id.swGluten);


        // Obtener el producto desde el intent
        product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            // Mostrar los detalles del producto
            productNameTextView.setText(product.getName());
            productDetailsTextView.setText(product.getDescription());
            swLactose.setChecked(product.isLactose());
            swGluten.setChecked(product.isGluten());
        }

        // Botón para editar el producto
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailsActivity.this, EditProductActivity.class);
            intent.putExtra("producto", product);
            // Usamos el código de solicitud 1 directamente
            startActivity(intent);
        });

        // Botón para volver a la actividad anterior
        backBtn.setOnClickListener(v -> {
            finish(); // Simplemente cierra esta actividad y vuelve a la anterior
        });
    }

    // Método para recibir el resultado de la edición
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtener el producto actualizado desde el intent
                Product productoActualizado = (Product) data.getSerializableExtra("producto_actualizado");
                if (productoActualizado != null) {
                    // Actualizar el objeto producto con los nuevos datos
                    product = productoActualizado;

                    // Refrescar la información en la pantalla de detalles
                    productNameTextView.setText(product.getName());
                    productDetailsTextView.setText(product.getDescription());
                    swLactose.setChecked(product.isLactose());
                    swGluten.setChecked(product.isGluten());
                }
            }
        }
    }
}
