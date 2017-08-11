package jorgemonzon.pruebatecnica.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jorgemonzon.pruebatecnica.R;

/**
 * Created by jorge on 11/08/17.
 */

public class CrearNuevoFragment extends Fragment {

    public CrearNuevoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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
        return inflater.inflate(R.layout.activity_crear_nuevo, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


    }
}
