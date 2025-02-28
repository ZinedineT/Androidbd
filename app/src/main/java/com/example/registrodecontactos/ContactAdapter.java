package com.example.registrodecontactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private OnDeleteClickListener onDeleteClickListener; // Agregar interfaz para eliminar

    public ContactAdapter(List<Contact> contactList, OnDeleteClickListener onDeleteClickListener) {
        this.contactList = contactList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.tvName.setText(contact.getNombre());
        holder.tvPhone.setText(contact.getTelefono());

        // Evento para eliminar
        holder.btnDelete.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(contact.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void eliminarContacto(int position) {
        contactList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;
        ImageButton btnDelete;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            btnDelete = itemView.findViewById(R.id.deletebutton); // Asegurar que se encuentre el botón
        }
    }

    // Interfaz para manejar la eliminación
    public interface OnDeleteClickListener {
        void onDeleteClick(int contactId, int position);
    }
}
