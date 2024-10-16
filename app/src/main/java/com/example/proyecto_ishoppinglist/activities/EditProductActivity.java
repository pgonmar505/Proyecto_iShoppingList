package com.example.proyecto_ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_ishoppinglist.R;
import com.example.proyecto_ishoppinglist.database.Database;
import com.example.proyecto_ishoppinglist.models.Product;

public class EditProductActivity extends AppCompatActivity {
    private EditText nombreEditText, detallesEditText;
    private Button saveBtn, cancelBtn;
    private Product producto;
    private Switch swLactose, swGluten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        // Inicialización de los campos de entrada
        nombreEditText = findViewById(R.id.etName);
        detallesEditText = findViewById(R.id.etDetails);
        saveBtn = findViewById(R.id.btnSave);
        cancelBtn = findViewById(R.id.btnCancel);
        swLactose = findViewById(R.id.swLactose);
        swGluten = findViewById(R.id.swGluten);

        // Obtener el producto desde el Intent
        producto = (Product) getIntent().getSerializableExtra("producto");

        if (producto != null) {
            // Mostrar los datos actuales del producto en los campos de texto
            nombreEditText.setText(producto.getName());
            detallesEditText.setText(producto.getDescription());
            swLactose.isChecked();
            swGluten.isChecked();
        }

        // Botón para guardar los cambios
        saveBtn.setOnClickListener(v -> {
            // Obtener los nuevos valores introducidos por el usuario
            String nuevoNombre = nombreEditText.getText().toString().trim();
            String nuevaDescripcion = detallesEditText.getText().toString().trim();
            swLactose.setChecked(producto.isLactose());
            swGluten.setChecked(producto.isGluten());

            // Verificar que los campos no están vacíos
            if (nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Actualizar los valores del producto
            producto.setName(nuevoNombre);
            producto.setDescription(nuevaDescripcion);
            swLactose.setChecked(producto.isLactose());
            swGluten.setChecked(producto.isGluten());

            // Guardar el producto actualizado en la base de datos
            Database.updateProduct(producto); // Actualiza el producto en la base de datos

            // Mostrar un mensaje de confirmación
            Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();

            // Redirigir de vuelta a la actividad anterior (ProductDetailsActivity)
            Intent resultIntent = new Intent();
            resultIntent.putExtra("producto_actualizado", producto);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Botón para cancelar y volver
        cancelBtn.setOnClickListener(v -> finish());
    }
}
