package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.ListAdapter;
import Controller.AppController;
import model.Topic;

public class TopicsView extends AppCompatActivity {

    private ListView mainListView;
    private ArrayList<Topic> fullList = AppController.getInstance().getData();
    private ArrayList<Topic> list = fullList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        loadList();
        loadSearchFilter();
        editTitle();
    }

    private void editTitle() {
        TextView text = (TextView) findViewById(R.id.listTitle);
        text.setText("Topics");
    }

    private void loadSearchFilter() {
        final EditText searchBar = (EditText) findViewById(R.id.searchFilter);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list = getSubListByName(searchBar.getText().toString());
                loadList();
                list = fullList;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    protected void loadList() {
        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdapter(this, R.layout.simple_row, list) {
            @Override
            public void input(final Object input, View view) {
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.simple_row_text);
                    rowTextView.setText(((Topic) input).getTopicName());
                }
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppController.getInstance().setTopicSelected(((Topic) parent.getItemAtPosition(position)));
                Intent intent = new Intent(TopicsView.this, InsideTopicView.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Topic> getSubListByName(String name){
        if (name.length()==0)return list;
        ArrayList<Topic> result = new ArrayList<>();
        for (Topic item:list){
            if(item.getTopicName().toLowerCase().contains(name.toLowerCase()))result.add(item);
        }
        return result;
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToTopicView(View v) {
        //Do Nothing
    }

}
