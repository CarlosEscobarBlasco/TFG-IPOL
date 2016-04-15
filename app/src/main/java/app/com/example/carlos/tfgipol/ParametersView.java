package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import adapters.MyListAdapter;
import dataCollector.JSONCollector;
import dataCollector.MockJSONCollector;
import model.RowParametersBuilder;

public class ParametersView extends AppCompatActivity {

    private ListView mainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        loadList();
        runAction();
    }

    private void runAction() {
        Button button = (Button) findViewById(R.id.run_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParametersView.this, ResultView.class);
                startActivity(intent);
            }
        });
    }

    private void loadList() {
        ArrayList<JSONObject> list = obtainList();
        mainListView = (ListView) findViewById(R.id.inputList);
        mainListView.setAdapter(new MyListAdapter(this, R.layout.input_row, list) {
            @Override
            public void input(Object input, View view) {
                new RowParametersBuilder(getApplicationContext()).createRow((JSONObject) input, view);
            }
        });
    }

    private ArrayList<JSONObject> obtainList() {
        ArrayList<JSONObject> list = new ArrayList<>();
        try {
            JSONCollector jsonCollector = new JSONCollector("http://serdis.dis.ulpgc.es/~asalgado/nlmeans.json");
            JSONArray jsonParams = new JSONObject(jsonCollector.execute().get()).getJSONArray("params");
            //JSONArray jsonParams = new MockJSONCollector().getJSON().getJSONArray("params");
            for (int i = 0; i < jsonParams.length(); i++) {
                list.add(jsonParams.getJSONObject(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
