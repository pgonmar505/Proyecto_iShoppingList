package com.example.proyecto_ishoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto_ishoppinglist.R;
import com.example.proyecto_ishoppinglist.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> products;
    private OnProductPendingStatusChangedListener listener;

    public ProductAdapter(Context context, ArrayList<Product> products, OnProductPendingStatusChangedListener listener) {
        super(context, 0, products);
        this.products = products;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener el producto actual
        Product product = getItem(position);

        // Inflar el layout personalizado
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_layout, parent, false);
        }

        // Referenciar los componentes del layout
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductDetails = convertView.findViewById(R.id.tvProductDetails);  // Campo "Detalles" ocupa el lugar de "Cantidad"
        Button btnChangePending = convertView.findViewById(R.id.btnChangePending);

        // Configurar los datos del producto
        tvProductName.setText(product.getName());
        tvProductDetails.setText("Detalles: " + product.getDescription());  // Mostrar la descripción como "Detalles"

        // Botón para cambiar el estado de pendiente
        btnChangePending.setOnClickListener(v -> {
            product.setPending(false);  // Cambia el estado a no pendiente
            if (listener != null) {
                listener.onProductPendingStatusChanged(product);
            }
        });

        return convertView;
    }

    // Interfaz para notificar cuando el estado de pendiente cambia
    public interface OnProductPendingStatusChangedListener {
        void onProductPendingStatusChanged(Product product);
    }
}
