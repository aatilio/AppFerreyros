package araho.com.example.database.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import araho.com.example.database.R;
import araho.com.example.database.VerActivity;
import araho.com.example.database.entidades.Maquinarias;

public class ListaMaquinariassAdapter extends RecyclerView.Adapter<ListaMaquinariassAdapter.MaquinariasViewHolder> {

    ArrayList<Maquinarias> listaMaquinarias;
    ArrayList<Maquinarias> listaOriginal;

    public ListaMaquinariassAdapter(ArrayList<Maquinarias> listaContactos) {
        this.listaMaquinarias = listaContactos; //le asigna lo que tiene el constructor

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaContactos);
    }

    @NonNull
    @Override
    public MaquinariasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_maquinaria, null, false);
        return new MaquinariasViewHolder(view);
    }

    //asignamos los elementos que corresponde para cada uno de los atributos
    @Override
    public void onBindViewHolder(@NonNull MaquinariasViewHolder holder, int position) {
        holder.viewCodigo.setText(listaMaquinarias.get(position).getCodigo());
        holder.viewNombre.setText(listaMaquinarias.get(position).getNombre());
        holder.viewCapacidad.setText(listaMaquinarias.get(position).getCapacidad());
        holder.viewPeso.setText(listaMaquinarias.get(position).getPeso());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaMaquinarias.clear();
            listaMaquinarias.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Maquinarias> collecion = listaMaquinarias.stream()
                        .filter(i -> i.getCodigo().toLowerCase()   //filter(i -> i.getDNI() -> para espesificar a que variable esta buscando
                        .contains(txtBuscar.toLowerCase()))    //toLowerCase() -> para convertir el texto en minuscula, si se busca por nombre
                        .collect(Collectors.toList());
                listaMaquinarias.clear();
                listaMaquinarias.addAll(collecion);
            } else {
                for (Maquinarias c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaMaquinarias.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMaquinarias.size();
    }

    public class MaquinariasViewHolder extends RecyclerView.ViewHolder {

        TextView viewCodigo, viewNombre, viewCapacidad, viewPeso;

        public MaquinariasViewHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigo = itemView.findViewById(R.id.viewCodigo);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCapacidad = itemView.findViewById(R.id.viewCapacidad);
            viewPeso = itemView.findViewById(R.id.viewPeso);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaMaquinarias.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
