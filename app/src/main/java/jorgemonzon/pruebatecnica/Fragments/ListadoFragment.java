package jorgemonzon.pruebatecnica.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import jorgemonzon.pruebatecnica.Adapters.ListaUsuariosAdapter;
import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;

/**
 * Created by jorge on 11/08/17.
 */

public class ListadoFragment extends Fragment implements IDataListaUsers{

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public ListadoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListadoFragment newInstance() {
        ListadoFragment fragment = new ListadoFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_listado, container, false);


    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        obtenerListaUsuarios();

    }



    public void obtenerListaUsuarios(){

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getListaUsers(this);

    }

    private void pintarListaUsuarios(List<UserItem> usuarios) {

        recycler = (RecyclerView)getActivity().findViewById(R.id.reciclador_activity_listado);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ListaUsuariosAdapter(usuarios);
        recycler.setAdapter(adapter);
    }
    @Override
    public void conexionCorrecta(List<UserItem> listaUsuarios) {

        pintarListaUsuarios(listaUsuarios);

    }

    @Override
    public void conexionIncorrecta() {

        Toast.makeText(getActivity(), "Fallo en la descarga de datos", Toast.LENGTH_LONG).show();

    }
}
