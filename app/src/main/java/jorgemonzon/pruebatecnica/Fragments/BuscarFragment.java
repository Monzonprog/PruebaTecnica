package jorgemonzon.pruebatecnica.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import jorgemonzon.pruebatecnica.Adapters.UsuarioAdapter;
import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataUser;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;


/**
 * Created by jorge on 11/08/17.
 */

public class BuscarFragment extends Fragment implements IDataUser {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public BuscarFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BuscarFragment newInstance() {
        BuscarFragment fragment = new BuscarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_buscar, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        obtenerUsuario();

    }

    private void obtenerUsuario() {

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getUser(this, 489);
    }

    private void pintarUsuario(UserItem usuario) {

        recycler = (RecyclerView)getActivity().findViewById(R.id.reciclador_activity_buscar);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new UsuarioAdapter(usuario);
        recycler.setAdapter(adapter);
    }

    @Override
    public void conexionCorrecta(UserItem usuario) {

        pintarUsuario(usuario);

    }

    @Override
    public void conexionIncorrecta() {

        Toast.makeText(getActivity(), "Fallo en la descarga de datos", Toast.LENGTH_LONG).show();
    }
}
