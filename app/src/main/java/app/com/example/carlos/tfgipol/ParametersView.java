package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.MyListAdapter;
import dataCollector.MockJSONCollector;
import model.RowParametersBuilder;

public class ParametersView extends AppCompatActivity {

    private ListView mainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        loadList();
    }

    private void loadList() {
        ArrayList<JSONObject> list = obtainList();
        mainListView = (ListView) findViewById(R.id.inputList);
        mainListView.setAdapter(new MyListAdapter(this, R.layout.input_row, list) {
            @Override
            public void input(Object input, View view) {
                new RowParametersBuilder(getApplicationContext(),this).createRow((JSONObject) input,view);
            }
        });
    }

    private ArrayList<JSONObject> obtainList() {
        ArrayList<JSONObject> list = new ArrayList<>();
        try {
            JSONArray jsonParams = new MockJSONCollector().getJSON().getJSONArray("params");
            for (int i = 0; i < jsonParams.length(); i++) {
                list.add(jsonParams.getJSONObject(i));
            }
        } catch (JSONException e) {
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
