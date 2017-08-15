package jorgemonzon.pruebatecnica.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jorgemonzon.pruebatecnica.Adapters.ListaUsuariosAdapter;
import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.Interfaces.IDataUser;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserBorrar;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserModificar;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserOpcionesCard;
import jorgemonzon.pruebatecnica.R;
import jorgemonzon.pruebatecnica.Utils.ConexionManager;

/**
 * Created by jorge on 11/08/17.
 */

public class ListadoFragment extends Fragment implements IDataListaUsers, IDataUserOpcionesCard,IDataUserBorrar, IDataUserModificar, IDataUser {

    private RecyclerView recycler;
    private ListaUsuariosAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<UserItem> usuariosTotales;
    private ArrayList<UserItem> usuariosFiltrados;
    private TextInputEditText filtrado;
    private EditText fechaEditTextDialog, horaEditTextDialog, nombreEditTextDialog;
    private Button buttonCancelarDialog, buttonGuardarDialog;
    private int usuario;
    private String fechaParaGuardar;


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
    public void conexionCorrecta(final UserItem Usuario) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_modificar);
        nombreEditTextDialog = (EditText) dialog.findViewById(R.id.nombreEditTextDialog);
        fechaEditTextDialog = (EditText) dialog.findViewById(R.id.fechaEditTextDialog);
        horaEditTextDialog = (EditText) dialog.findViewById(R.id.horaEditTextDialog);

        String fechaCompleta = Usuario.getBirthdate().toString();
        String [] fechaYhora =  fechaCompleta.split(" ");
        fechaEditTextDialog.setText(fechaYhora[0]);
        horaEditTextDialog.setText(fechaYhora[1]);
        nombreEditTextDialog.setText(Usuario.getName());
        usuario = Usuario.getId();

        buttonCancelarDialog = (Button) dialog.findViewById(R.id.buttonCancelarDialog);
        buttonGuardarDialog = (Button) dialog.findViewById(R.id.buttonGuardarDialog);

        dialog.show();

        fechaEditTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mYear, mMonth, mDay;

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                fechaEditTextDialog.setText(year + "-" + (monthOfYear + 1) +
                                        "-" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();



            }
        });

        horaEditTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int mHour, mMinute;

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                horaEditTextDialog.setText( String.format("%02d:%02d", hourOfDay,
                                        minute));


                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });


        buttonCancelarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

            }
        });

        buttonGuardarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actualizarUsuario();
                dialog.cancel();

            }
        });
    }

    public void actualizarUsuario(){

        fechaParaGuardar = fechaEditTextDialog.getText().toString() + "T" +
                horaEditTextDialog.getText().toString();

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.updateUser(this, usuario,nombreEditTextDialog.getText()
                .toString(), fechaParaGuardar);

    }

    @Override
    public void conexionIncorrecta() {

        Toast.makeText(getActivity(), getString(R.string.conexionIncorrecta), Toast.LENGTH_LONG).show();

    }

    @Override
    public void conexionNoEncontrado() {

    }

    @Override
    public void modificaUser(String id) {

        ConexionManager conexion = new ConexionManager("http://hello-world.innocv.com/api/user/");
        conexion.getUser(this, Integer.valueOf(id));
    }

    @Override
    public void conexionCorrectaModificarUser(UserItem usuario) {

        obtenerListaUsuarios();
        Toast.makeText(getActivity(),getString(R.string.okModificar), Toast.LENGTH_LONG).show();

    }

    @Override
    public void conexionIncorrectaModificarUser() {

        Toast.makeText(getActivity(),getString(R.string.errorModificar), Toast.LENGTH_LONG).show();

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
