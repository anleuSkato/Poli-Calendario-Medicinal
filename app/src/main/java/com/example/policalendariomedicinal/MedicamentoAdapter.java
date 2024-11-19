package com.example.policalendariomedicinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoAdapter.MedicamentoViewHolder> {

    private List<Medicamento> medicamentos;

    public MedicamentoAdapter(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @NonNull
    @Override
    public MedicamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicamento, parent, false);
        return new MedicamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoViewHolder holder, int position) {
        Medicamento medicamento = medicamentos.get(position);
        holder.nombreTextView.setText("Nombre: " + medicamento.getNombre());
        holder.dosisTextView.setText("Dosis: " + medicamento.getDosis());
        holder.horaTextView.setText("Hora: " + medicamento.getHora());
        holder.diasTextView.setText("Días: " + medicamento.getDias());
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    static class MedicamentoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, dosisTextView, horaTextView, diasTextView;

        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            dosisTextView = itemView.findViewById(R.id.dosisTextView);
            horaTextView = itemView.findViewById(R.id.horaTextView);
            diasTextView = itemView.findViewById(R.id.diasTextView);
        }
    }
}
