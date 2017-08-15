package jorgemonzon.pruebatecnica.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserCrear;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;

/**
 * Created by jorge on 11/08/17.
 */

public class CrearNuevoFragment extends Fragment implements
        View.OnClickListener, IDataUserCrear {

    private TextInputEditText nombreUsuarioCrearNuevoFragment;
    private Button fechaButtonCrearNuevo, horaButtonCrearNuevo;
    private TextView fechaTextViewCrearNuevo, horaTextViewCrearNuevo;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private FloatingActionButton crearButtonCrearNuevoFragment;
    private TextView Id;
    private TextView Name;
    private TextView Birthdate;
    private CardView usuarioCrearNuevoFragment;


    public CrearNuevoFragment() {
        // Required empty public constructor
    }

    public static CrearNuevoFragment newInstance() {
        CrearNuevoFragment fragment = new CrearNuevoFragment();
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
        return inflater.inflate(R.layout.fragment_crear_nuevo, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        nombreUsuarioCrearNuevoFragment = (TextInputEditText) getActivity().findViewById(R.id.
                nombreUsuarioCrearNuevoFragment);
        fechaButtonCrearNuevo = (Button) getActivity().findViewById(R.id.fechaButtonCrearNuevo);
        horaButtonCrearNuevo = (Button) getActivity().findViewById(R.id.horaButtonCrearNuevo);
        fechaTextViewCrearNuevo = (TextView) getActivity().findViewById(R.id.fechaTextViewCrearNuevo);
        horaTextViewCrearNuevo = (TextView) getActivity().findViewById(R.id.horaTextViewCrearNuevo);
        crearButtonCrearNuevoFragment = (FloatingActionButton) getActivity().
                findViewById(R.id.crearButtonCrearNuevoFragment);

        fechaButtonCrearNuevo.setOnClickListener(this);
        horaButtonCrearNuevo.setOnClickListener(this);
        crearButtonCrearNuevoFragment.setOnClickListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        animacionFAB(crearButtonCrearNuevoFragment);

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

    @Override
    public void onClick(View v) {

        if (v == crearButtonCrearNuevoFragment) {

            if (verificarCampos()) {

                recogerDatosCrearUsuario();
                Toast.makeText(getActivity(), "Correcto", Toast.LENGTH_LONG).show();


            } else {

                Toast.makeText(getActivity(), getString(R.string.camposIncompletos), Toast.LENGTH_LONG).show();

            }
        }

        if (v == fechaButtonCrearNuevo) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            fechaTextViewCrearNuevo.setText(year + "-" + (monthOfYear + 1) +  "-" + dayOfMonth);
                                   // dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == horaButtonCrearNuevo) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            //horaTextViewCrearNuevo.setText(hourOfDay + ":" + minute);

                            horaTextViewCrearNuevo.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    private boolean verificarCampos() {

        if ((nombreUsuarioCrearNuevoFragment.getText().toString().isEmpty()) ||
                fechaTextViewCrearNuevo.getText().toString().isEmpty() ||
                horaTextViewCrearNuevo.getText().toString().isEmpty()) {

            return false;

        } else {

            return true;
        }
    }

    public void recogerDatosCrearUsuario(){

        String nombreParaGuardar = nombreUsuarioCrearNuevoFragment.getText().toString();
        String fechaParaGuardar = fechaTextViewCrearNuevo.getText().toString() + "T" +
                horaTextViewCrearNuevo.getText().toString() + ":00";

        crearUsuario(nombreParaGuardar,fechaParaGuardar);


    }

    public void crearUsuario(String nombreParaGuardar, String fechaParaGuardar) {

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.createUser(this, nombreParaGuardar, fechaParaGuardar);
    }

    @Override
    public void conexionCorrectaCrearUser(UserItem usuario) {

        usuarioCrearNuevoFragment = (CardView)getActivity().findViewById(R.id.
                usuarioCrearNuevoFragment);

        pintarUsuario(usuario);

        usuarioCrearNuevoFragment.setVisibility(View.VISIBLE);

    }

    @Override
    public void conexionIncorrectaCrearUser() {

        Toast.makeText(getActivity(), getString(R.string.errorCrearUsuario), Toast.LENGTH_LONG).show();

    }

    private void pintarUsuario(UserItem usuario) {

        Id = (TextView)getActivity().findViewById(R.id.idTextViewCrear);
        Name = (TextView)getActivity().findViewById(R.id.nombreTextViewCrear);
        Birthdate = (TextView)getActivity().findViewById(R.id.fechaTextViewCrear);

        Id.setText(Integer.toString(usuario.getId()));
        Name.setText(usuario.getName());
        Birthdate.setText(usuario.getBirthdate());
    }
}

