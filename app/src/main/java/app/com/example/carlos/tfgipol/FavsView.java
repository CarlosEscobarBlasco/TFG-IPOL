package app.com.example.carlos.tfgipol;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.Lista_adaptador;

public class FavsView extends Activity {

    private ListView mainListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs_view);
        ArrayList<String> datos = new ArrayList<>();
        datos.add("Lunes");
        datos.add("Martes");
        datos.add("Miercoles");
        datos.add("Jueves");
        datos.add("Viernes");
        datos.add("Sabado");
        datos.add("Domingo");
        datos.add("Domingo");
        datos.add("Domingo");

        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new Lista_adaptador(this,R.layout.simplerow,datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText((String) entrada);
                }
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
