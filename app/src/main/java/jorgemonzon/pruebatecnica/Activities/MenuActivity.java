package jorgemonzon.pruebatecnica.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import jorgemonzon.pruebatecnica.Adapters.ListaAdapter;
import jorgemonzon.pruebatecnica.Adapters.UsuarioAdapter;
import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.Interfaces.IDataUser;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;

public class MenuActivity extends AppCompatActivity implements IDataListaUsers, IDataUser{

    private List<UserItem> usuarios;
    private UserItem usuario;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        obtenerListaUsuarios();

       // obtenerUsuario();






    }

    private void obtenerUsuario() {

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getUser(this, 489);
    }

    public void obtenerListaUsuarios(){

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getListaUsers(this);

    }

    private void pintarUsuario(UserItem usuario) {

        recycler = (RecyclerView)findViewById(R.id.reciclador);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new UsuarioAdapter(usuario);
        recycler.setAdapter(adapter);
    }

    private void pintarListaUsuarios(List<UserItem> usuarios) {

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

        pintarListaUsuarios(listaUsuarios);

    }

    @Override
    public void conexionIncorrecta() {

        Toast.makeText(this, "Fallo en la descarga de datos", Toast.LENGTH_LONG).show();

    }

    @Override
    public void conexionCorrecta(UserItem usuario) {

        pintarUsuario(usuario);
        Toast.makeText(this, usuario.toString(), Toast.LENGTH_LONG).show();


    }
}
