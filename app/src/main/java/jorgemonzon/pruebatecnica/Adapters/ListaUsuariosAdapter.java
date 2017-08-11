package jorgemonzon.pruebatecnica.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.R;

/**
 * Created by jorge on 11/08/17.
 */

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuariosViewHolder> {

    private List<UserItem> items;

    public static class UsuariosViewHolder extends RecyclerView.ViewHolder {

        public TextView Id;
        public TextView Name;
        public TextView Birthdate;

        public UsuariosViewHolder(View v) {
            super(v);
            Id = (TextView)v.findViewById(R.id.idTextViewTarjetaUsuario);
            Name = (TextView)v.findViewById(R.id.nombreTextViewTarjetaUsuario);
            Birthdate = (TextView)v.findViewById(R.id.fechaTextViewTarjetaUsuario);
        }
    }

    public ListaUsuariosAdapter(List<UserItem> items){

        this.items = items;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.usuario_tarjeta, viewGroup, false);
        return new UsuariosViewHolder(v);

    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder viewholder, int i) {

        viewholder.Id.setText(convertirValor(i));
        viewholder.Name.setText(items.get(i).getName());
        viewholder.Birthdate.setText(items.get(i).getBirthdate());

    }

    public String convertirValor(int i){

        String ID = Integer.toString(items.get(i).getId());

        return ID;

    }


}
