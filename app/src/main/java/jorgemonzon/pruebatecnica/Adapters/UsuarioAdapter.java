package jorgemonzon.pruebatecnica.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.R;

/**
 * Created by jorge on 11/08/17.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private UserItem item;



    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {

        public TextView Id;
        public TextView Name;
        public TextView Birthdate;

        public UsuarioViewHolder(View v) {
            super(v);
            Id = (TextView) v.findViewById(R.id.idTextViewTarjetaUsuario);
            Name = (TextView) v.findViewById(R.id.nombreTextViewTarjetaUsuario);
            Birthdate = (TextView) v.findViewById(R.id.fechaTextViewTarjetaUsuario);
        }
    }

    public UsuarioAdapter(UserItem item) {

        this.item = item;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.usuario_tarjeta, viewGroup, false);
        return new UsuarioAdapter.UsuarioViewHolder(v);

    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.UsuarioViewHolder viewholder, int position) {

        viewholder.Id.setText(item.getId());
        viewholder.Name.setText(item.getName());
        viewholder.Birthdate.setText(item.getBirthdate());

    }



}

