package jorgemonzon.pruebatecnica.Adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserOpcionesCard;
import jorgemonzon.pruebatecnica.R;

/**
 * Created by jorge on 11/08/17.
 */

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuariosViewHolder> {

    private List<UserItem> items;
    private IDataUserOpcionesCard listener;
    private TextWatcher listenerTextWatcher;

    public void setListener(TextWatcher textWatcher) {

        this.listenerTextWatcher = textWatcher;
    }


    public static class UsuariosViewHolder extends RecyclerView.ViewHolder {

        public TextView Id;
        public TextView Name;
        public TextView Birthdate;
        public ImageView EditarUser;
        public ImageView BorrarUser;

        public UsuariosViewHolder(View v) {
            super(v);
            Id = (TextView)v.findViewById(R.id.idTextViewTarjetaUsuario);
            Name = (TextView)v.findViewById(R.id.nombreTextViewTarjetaUsuario);
            Birthdate = (TextView)v.findViewById(R.id.fechaTextViewTarjetaUsuario);
            EditarUser = (ImageView) v.findViewById(R.id.editarImageViewTarjetaUsuario);
            BorrarUser = (ImageView) v.findViewById(R.id.borrarImageViewTarjetaUsuario);
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
    public void onBindViewHolder(UsuariosViewHolder viewholder, final int i) {

        viewholder.Id.setText(convertirValor(i));
        viewholder.Name.setText(items.get(i).getName());
        viewholder.Birthdate.setText(items.get(i).getBirthdate());
        viewholder.EditarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewholder.BorrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.borrarUser(convertirValor(i));
            }
        });

        viewholder.EditarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.modificaUser(convertirValor(i));

            }
        });

    }

    private String convertirValor(int i){

        String ID = String.valueOf(items.get(i).getId());

        return ID;

    }

    public void setListener (IDataUserOpcionesCard listener){
        this.listener = listener;
    }


}
