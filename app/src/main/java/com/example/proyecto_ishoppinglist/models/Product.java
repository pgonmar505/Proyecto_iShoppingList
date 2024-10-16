package com.example.proyecto_ishoppinglist.models;

import java.io.Serializable;

public class Product implements Serializable {
    private static int currentId = 1; // Auto-incremental ID
    private final int id;
    private String name;
    private String description;
    private boolean pending;
    private boolean lactose;
    private boolean gluten;

    // Constructor


    public Product(String name, String description, boolean pending, boolean lactose, boolean gluten) {
        this.id = currentId++;  // Asignar ID e incrementar
        this.name = name.toUpperCase();
        this.description = description;
        this.pending = pending;
        this.lactose = lactose;
        this.gluten = gluten;

    }

    // Getters y Setters
    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name.toUpperCase(); }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isPending() { return pending; }

    public void setPending(boolean pending) { this.pending = pending; }

    public boolean isLactose() { return lactose; }

    public void setLactose(boolean lactose) { this.lactose = lactose; }

    public boolean isGluten() { return gluten; }

    public void setGluten(boolean gluten) { this.gluten = gluten; }

    @Override
    public String toString() {
        return name + " - " + description + " - " + (pending ? "Pendiente" : "No pendiente") + "-" + (lactose? "Con lactosa" : "Sin Lactosa") + "-" + (gluten? "Con gluten" : "Sin gluten");
    }
}
