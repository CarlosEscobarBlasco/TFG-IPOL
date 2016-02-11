package app.com.example.carlos.tfgipol;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FavsView extends Activity {

    private ListView mainListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs_view);

        mainListView = (ListView) findViewById( R.id.listView_favs);
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune","Prueba","Prueba2"};

        ArrayList<String> planetList = new ArrayList<>();
        planetList.addAll( Arrays.asList(planets) );


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.simplerow,
                planetList);

        // 3. Call setListAdapter
        //setListAdapter(adapter);
        mainListView.setAdapter(adapter);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
