package com.example.proyecto_ishoppinglist.database;

import com.example.proyecto_ishoppinglist.models.Product;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Product> createdProducts = new ArrayList<>();
    private static ArrayList<Product> pendingProducts = new ArrayList<>();
    private static boolean initialized = false;

    // Método para inicializar los productos creados
    private static void initializeProducts() {
        if (!initialized) {
            // Añadir 30 productos de supermercado con nombre, descripción y estado pendiente
            createdProducts.add(new Product("Leche", "Leche entera de vaca", false, true, false));
            createdProducts.add(new Product("Pan", "Pan integral recién horneado", false, true, false));
            createdProducts.add(new Product("Huevos", "Huevos orgánicos, 12 unidades", true, true, false));
            createdProducts.add(new Product("Manzanas", "Manzanas Fuji frescas",  false, true, false));
            createdProducts.add(new Product("Queso", "Queso Cheddar madurado",  true, true, false));
            createdProducts.add(new Product("Yogur", "Yogur natural sin azúcar",  false, true, false));
            createdProducts.add(new Product("Cereal", "Cereal integral con avena",  false, true, false));
            createdProducts.add(new Product("Jugo de Naranja", "Jugo 100% natural",  true, true, false));
            createdProducts.add(new Product("Pollo", "Pechuga de pollo fresca",  false, true, false));
            createdProducts.add(new Product("Pasta", "Pasta integral espagueti",  false, true, false));
            createdProducts.add(new Product("Arroz", "Arroz basmati",  false, true, false));
            createdProducts.add(new Product("Tomates", "Tomates cherry frescos",  true, true, false));
            createdProducts.add(new Product("Patatas", "Patatas para asar",  false, true, false));
            createdProducts.add(new Product("Cebolla", "Cebollas amarillas",  true, true, false));
            createdProducts.add(new Product("Ajo", "Cabezas de ajo",  false, true, false));
            createdProducts.add(new Product("Aceite de Oliva", "Aceite de oliva extra virgen",  true, true, false));
            createdProducts.add(new Product("Sal", "Sal marina fina",  false, true, false));
            createdProducts.add(new Product("Pimienta", "Pimienta negra molida",  false, true, false));
            createdProducts.add(new Product("Harina", "Harina de trigo",  false, true, false));
            createdProducts.add(new Product("Azúcar", "Azúcar morena",  true, true, false));
            createdProducts.add(new Product("Lechuga", "Lechuga romana",  true, true, false));
            createdProducts.add(new Product("Zanahorias", "Zanahorias frescas",  false, true, false));
            createdProducts.add(new Product("Pepino", "Pepinos frescos",  false, true, false));
            createdProducts.add(new Product("Pimientos", "Pimientos rojos",  false, true, false));
            createdProducts.add(new Product("Aguacates", "Aguacates maduros",  true, true, false));
            createdProducts.add(new Product("Lentejas", "Lentejas verdes",  false, true, false));
            createdProducts.add(new Product("Garbanzos", "Garbanzos secos",  true, true, false));
            createdProducts.add(new Product("Café", "Café molido",  true, true, false));
            createdProducts.add(new Product("Té", "Té verde en bolsitas",  false, true, false));
            createdProducts.add(new Product("Galletas", "Galletas de avena",  true, true, false));

            initialized = true; // Evitar agregar productos múltiples veces
        }
    }

    public static ArrayList<Product> getCreatedProducts() {
        if (!initialized) {
            initializeProducts();
        }
        return createdProducts;
    }

    public static void addProduct(Product product) {
        createdProducts.add(product);
    }

    public static boolean productExists(String name) {
        for (Product p : createdProducts) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Product> getPendingProducts() {
        return pendingProducts;
    }

    public static void addToPending(Product product) {
        pendingProducts.add(product);
    }

    // Método para actualizar un producto en la base de datos
    public static void updateProduct(Product updatedProduct) {
        for (int i = 0; i < createdProducts.size(); i++) {
            if (createdProducts.get(i).getId() == updatedProduct.getId()) {
                createdProducts.set(i, updatedProduct); // Actualizar el producto en la lista
                break;
            }
        }
    }
}

