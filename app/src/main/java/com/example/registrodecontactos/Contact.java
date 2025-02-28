package com.example.registrodecontactos;

public class Contact {
    private int id;
    private String nombre;
    private String telefono;

    public Contact(int id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
}
