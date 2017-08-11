package jorgemonzon.pruebatecnica.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jorgemonzon.pruebatecnica.Adapters.ListaAdapter;
import jorgemonzon.pruebatecnica.Class.ListaUsersObject;
import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;

public class MenuActivity extends AppCompatActivity implements IDataListaUsers{

    private List<UserItem> usuarios;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        obtenerListaUsuarios();

        pintarListaUsuarios();


    }

    public void obtenerListaUsuarios(){

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getListaUsers(this);

    }

    private void pintarListaUsuarios() {

        recycler = (RecyclerView)findViewById(R.id.reciclador);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ListaAdapter(usuarios);
        recycler.setAdapter(adapter);
    }

    @Override
    public void conexionCorrecta(List<UserItem> listaUsuarios) {

        usuarios = listaUsuarios;


    }

    @Override
    public void conexionIncorrecta() {

    }
}
