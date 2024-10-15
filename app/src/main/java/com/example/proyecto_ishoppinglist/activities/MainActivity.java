package com.example.proyecto_ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_ishoppinglist.R;
import com.example.proyecto_ishoppinglist.adapters.ProductAdapter;
import com.example.proyecto_ishoppinglist.database.Database;
import com.example.proyecto_ishoppinglist.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductPendingStatusChangedListener {
    private ListView pendingProductsListView;
    private ProductAdapter adapter;
    private Button addProductBtn, addToPendingBtn;
    private ArrayList<Product> pendingProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pendingProductsListView = findViewById(R.id.lvPendingProducts);
        addProductBtn = findViewById(R.id.addProductBtn);
        addToPendingBtn = findViewById(R.id.addToPendingBtn);

        // Inicializar la lista de productos pendientes
        pendingProducts = new ArrayList<>();

        // Configurar el adaptador
        adapter = new ProductAdapter(this, pendingProducts, this);
        pendingProductsListView.setAdapter(adapter);

        // Cargar productos pendientes desde la base de datos
        loadPendingProducts();

        // Añadir nuevo producto
        addProductBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
            startActivity(intent);
        });

        // Añadir producto a pendientes
        addToPendingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddToPendingActivity.class);
            intent.putExtra("productos", Database.getCreatedProducts());
            startActivity(intent);  // Usamos startActivityForResult para recibir el resultado
        });

        // Mostrar detalles del producto al hacer clic en el item
        pendingProductsListView.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = pendingProducts.get(position);

            // Crear un intent para abrir ProductDetailsActivity
            Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
            intent.putExtra("product", selectedProduct);
            startActivity(intent);
        });
    }

    // Método para cargar los productos pendientes
    private void loadPendingProducts() {
        pendingProducts.clear();
        pendingProducts.addAll(getPendingProducts());
        adapter.notifyDataSetChanged();
    }

    // Obtener productos pendientes de la base de datos
    private ArrayList<Product> getPendingProducts() {
        ArrayList<Product> allProducts = Database.getCreatedProducts();
        ArrayList<Product> pendingProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.isPending()) {
                pendingProducts.add(product);
            }
        }
        return pendingProducts;
    }

    // Sobrescribir onResume para actualizar la lista al regresar a MainActivity
    @Override
    protected void onResume() {
        super.onResume();
        loadPendingProducts();
    }

    // Manejar el resultado de AddToPendingActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            // Obtener el producto seleccionado desde AddToPendingActivity
            Product selectedProduct = (Product) data.getSerializableExtra("selectedProduct");

            if (selectedProduct != null) {
                // Si el producto no está en la lista, añadirlo
                if (!productAlreadyInPendingList(selectedProduct)) {
                    pendingProducts.add(selectedProduct);
                    adapter.notifyDataSetChanged();  // Refrescar la lista
                }
            }
        }
    }

    // Comprobar si el producto ya está en la lista de pendientes
    private boolean productAlreadyInPendingList(Product product) {
        for (Product pendingProduct : pendingProducts) {
            if (pendingProduct.getId() == product.getId()) {
                return true;  // Si el ID ya está, no añadir
            }
        }
        return false;
    }

    // Actualizar el producto cuando cambia su estado de pendiente
    @Override
    public void onProductPendingStatusChanged(Product product) {
        pendingProducts.remove(product);  // Eliminar el producto cuando cambia de estado
        adapter.notifyDataSetChanged();  // Refrescar la lista
    }
}
