package com.example.proyecto_ishoppinglist.models;

import java.io.Serializable;

public class Product implements Serializable {
    private static int currentId = 1; // Auto-incremental ID
    private final int id;
    private String name;
    private String description;
    private boolean pending;

    // Constructor
    public Product(String name, String description, boolean pending) {
        this.id = currentId++;  // Asignar ID e incrementar
        this.name = name.toUpperCase();
        this.description = description;
        this.pending = pending;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name.toUpperCase(); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isPending() { return pending; }
    public void setPending(boolean pending) { this.pending = pending; }

    @Override
    public String toString() {
        return name + " - " + description + " - " + (pending ? "Pendiente" : "No pendiente");
    }
}
