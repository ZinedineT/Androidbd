package com.example.registrodecontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ContactDatabaseHelper db;
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ContactDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddContact = findViewById(R.id.btnAddContact);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar contactos en la lista
        loadContacts();

        btnAddContact.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts(); // Recargar la lista cuando regrese de agregar un contacto
    }

    private void loadContacts() {
        List<Contact> contacts = db.getAllContacts();
        contactAdapter = new ContactAdapter(contacts, this::confirmarEliminacion);
        recyclerView.setAdapter(contactAdapter);
    }

    private void confirmarEliminacion(int contactId, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Seguro que deseas eliminar este contacto?")
                .setPositiveButton("Sí", (dialog, which) -> eliminarContacto(contactId, position))
                .setNegativeButton("No", null)
                .show();
    }

    private void eliminarContacto(int contactId, int position) {
        db.deleteContact(contactId);
        contactAdapter.eliminarContacto(position);
    }
}
