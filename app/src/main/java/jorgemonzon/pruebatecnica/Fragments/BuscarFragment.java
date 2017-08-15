package jorgemonzon.pruebatecnica.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataUser;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;


/**
 * Created by jorge on 11/08/17.
 */

public class BuscarFragment extends Fragment implements IDataUser {

    private TextView Id;
    private TextView Name;
    private TextView Birthdate;
    private TextInputEditText usuarioIntroducido;
    private CardView usuarioCardViewBuscarFragment;
    private FloatingActionButton buscarFAB;

    public BuscarFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        usuarioIntroducido = (TextInputEditText)view.findViewById(R.id.
                buscarUsuarioBuscarFragment);
        buscarFAB = (FloatingActionButton)view.findViewById(R.id.buscarButtonBuscarFragment);

        buscarFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usuarioIntroducido.getText().toString().isEmpty()){

                    Toast.makeText(getActivity(),getString(R.string.textoVacio), Toast.LENGTH_LONG)
                            .show();
                }else{

                    buscarUsuario();

                }
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

    }

    @Override
    public void onResume() {
        super.onResume();
        animacionFAB(buscarFAB);

    }
    public void animacionFAB(FloatingActionButton fab){

        fab.setScaleX(0);
        fab.setScaleY(0);


        final Interpolator interpolador = AnimationUtils.loadInterpolator(getActivity().getBaseContext(),
                android.R.interpolator.fast_out_slow_in);

        fab.animate()
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(interpolador)
                .setDuration(600)
                .setStartDelay(1000);
    }

    public void buscarUsuario(){

        String usuarioBuscar = usuarioIntroducido.getText().toString();

        int usuarioBuscarINT=  Integer.valueOf(usuarioBuscar);

        obtenerUsuario(usuarioBuscarINT);

    }

    private void obtenerUsuario(int usuarioBuscarINT) {

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getUser(this, usuarioBuscarINT);
    }

    @Override
    public void conexionCorrecta(UserItem usuario) {

        usuarioCardViewBuscarFragment = (CardView)getActivity().findViewById(R.id.usuarioCardViewBuscarFragment);

        pintarUsuario(usuario);

        usuarioCardViewBuscarFragment.setVisibility(View.VISIBLE);


    }

    private void pintarUsuario(UserItem usuario) {

        Id = (TextView)getActivity().findViewById(R.id.idTextViewBuscar);
        Name = (TextView)getActivity().findViewById(R.id.nombreTextViewBuscar);
        Birthdate = (TextView)getActivity().findViewById(R.id.fechaTextViewBuscar);

        Id.setText(Integer.toString(usuario.getId()));
        Name.setText(usuario.getName());
        Birthdate.setText(usuario.getBirthdate());
    }

    @Override
    public void conexionIncorrecta() {

        Toast.makeText(getActivity(), getString(R.string.errorDescarga), Toast.LENGTH_LONG).show();
    }

    @Override
    public void conexionNoEncontrado() {
        Toast.makeText(getActivity(), getString(R.string.usuarioNoEncontrado), Toast.LENGTH_LONG).show();

    }

    public void limpiar(){

        usuarioCardViewBuscarFragment.setVisibility(View.INVISIBLE);
        usuarioIntroducido.setText("");

    }
}
