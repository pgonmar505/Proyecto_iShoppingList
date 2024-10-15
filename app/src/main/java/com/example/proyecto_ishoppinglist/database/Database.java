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
            createdProducts.add(new Product("Leche", "Leche entera de vaca", false));
            createdProducts.add(new Product("Pan", "Pan integral recién horneado", false));
            createdProducts.add(new Product("Huevos", "Huevos orgánicos, 12 unidades", true));
            createdProducts.add(new Product("Manzanas", "Manzanas Fuji frescas",  false));
            createdProducts.add(new Product("Queso", "Queso Cheddar madurado",  true));
            createdProducts.add(new Product("Yogur", "Yogur natural sin azúcar",  false));
            createdProducts.add(new Product("Cereal", "Cereal integral con avena",  false));
            createdProducts.add(new Product("Jugo de Naranja", "Jugo 100% natural",  true));
            createdProducts.add(new Product("Pollo", "Pechuga de pollo fresca",  false));
            createdProducts.add(new Product("Pasta", "Pasta integral espagueti",  false));
            createdProducts.add(new Product("Arroz", "Arroz basmati",  false));
            createdProducts.add(new Product("Tomates", "Tomates cherry frescos",  true));
            createdProducts.add(new Product("Patatas", "Patatas para asar",  false));
            createdProducts.add(new Product("Cebolla", "Cebollas amarillas",  true));
            createdProducts.add(new Product("Ajo", "Cabezas de ajo",  false));
            createdProducts.add(new Product("Aceite de Oliva", "Aceite de oliva extra virgen",  true));
            createdProducts.add(new Product("Sal", "Sal marina fina",  false));
            createdProducts.add(new Product("Pimienta", "Pimienta negra molida",  false));
            createdProducts.add(new Product("Harina", "Harina de trigo",  false));
            createdProducts.add(new Product("Azúcar", "Azúcar morena",  true));
            createdProducts.add(new Product("Lechuga", "Lechuga romana",  true));
            createdProducts.add(new Product("Zanahorias", "Zanahorias frescas",  false));
            createdProducts.add(new Product("Pepino", "Pepinos frescos",  false));
            createdProducts.add(new Product("Pimientos", "Pimientos rojos",  false));
            createdProducts.add(new Product("Aguacates", "Aguacates maduros",  true));
            createdProducts.add(new Product("Lentejas", "Lentejas verdes",  false));
            createdProducts.add(new Product("Garbanzos", "Garbanzos secos",  true));
            createdProducts.add(new Product("Café", "Café molido",  true));
            createdProducts.add(new Product("Té", "Té verde en bolsitas",  false));
            createdProducts.add(new Product("Galletas", "Galletas de avena",  true));

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

