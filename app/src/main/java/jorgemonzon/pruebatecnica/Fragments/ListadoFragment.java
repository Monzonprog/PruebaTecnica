package jorgemonzon.pruebatecnica.Fragments;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jorgemonzon.pruebatecnica.Adapters.ListaUsuariosAdapter;
import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserBorrar;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserModificar;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;

/**
 * Created by jorge on 11/08/17.
 */

public class ListadoFragment extends Fragment implements IDataListaUsers, IDataUserModificar,IDataUserBorrar {

    private RecyclerView recycler;
    private ListaUsuariosAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<UserItem> usuariosTotales;
    private ArrayList<UserItem> usuariosFiltrados;
    private TextInputEditText filtrado;


    public ListadoFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_listado, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

    }

    @Override
    public void onResume() {
        super.onResume();
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
        adapter.setListener(this);
        recycler.setAdapter(adapter);
    }
    @Override
    public void conexionCorrecta(List<UserItem> listaUsuarios) {
        filtrado = (TextInputEditText)getActivity().
                findViewById(R.id.filtradoTextImputTextActivityListado);
        usuariosTotales = (ArrayList<UserItem>) listaUsuarios;
        pintarListaUsuarios(listaUsuarios);

        filtrado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                usuariosFiltrados = new ArrayList<>();
                String buscar = filtrado.getText().toString();
                if (usuariosTotales == null) {
                    return;
                }
                for (UserItem userItem: usuariosTotales) {
                    if (String.valueOf(userItem.getId()).contains(buscar) || userItem.getName().contains(buscar)) {
                        usuariosFiltrados.add(userItem);
                    }
                }
                adapter = new ListaUsuariosAdapter(usuariosFiltrados);
                adapter.setListener(this);
                recycler.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void conexionIncorrecta() {

        Toast.makeText(getActivity(), getString(R.string.conexionIncorrecta), Toast.LENGTH_LONG).show();

    }

    @Override
    public void modificaUser(String id) {

    }

    @Override
    public void borrarUser(String id) {

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.removeUser(this, Integer.valueOf(id));
    }

    @Override
    public void conexionCorrectaBorrarUser() {

        obtenerListaUsuarios();
        Toast.makeText(getActivity(),getString(R.string.okBorrar), Toast.LENGTH_LONG).show();

    }

    @Override
    public void conexionIncorrectaBorrarUser() {

        Toast.makeText(getActivity(),getString(R.string.falloBorrar), Toast.LENGTH_LONG).show();

    }
}
