package jorgemonzon.pruebatecnica.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import jorgemonzon.pruebatecnica.R;

public class MainActivity extends AppCompatActivity {

    private Button espanol;
    private Button ingles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        espanol = (Button)findViewById(R.id.buttonEspanolActivityMain);
        ingles = (Button)findViewById(R.id.buttonInglesActivityMain);


        espanol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale localizacion = new Locale("es", "ES"); //Seleccionamos ubicación para mostrar
                //activity en Español

                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext()
                        .getResources().getDisplayMetrics());

                lanzarMenu(v);
            }
        });

        ingles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale localizacion = new Locale("en", "EN");//Seleccionamos ubicación para mostrar
                //activity en Inglés

                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext()
                        .getResources().getDisplayMetrics());

                lanzarMenu(v);


            }
        });
    }

    public void lanzarMenu(View v){

        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
