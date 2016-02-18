package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import adapters.ListAdapter;
import model.RowData;

/**
 * Created by Carlos on 18/02/2016.
 */
public abstract class MyActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayList<RowData> fullList = setList();
    private ArrayList<RowData> list = fullList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setThisContentView());
        loadList();
        loadSearchFilter();
    }

    private void loadSearchFilter() {
        final EditText searchBar = (EditText) findViewById(R.id.searchFilter);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list = getSubListByName(searchBar.getText().toString());
                loadList();
                list = fullList;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected void loadList() {
        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdapter(this, R.layout.text_and_button_row, list) {
            @Override
            public void input(final Object input, View view) {
                final ImageButton favButton = (ImageButton) view.findViewById(R.id.rowFavButton);
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText(((RowData) input).getText());
                    favButton.setImageResource(((RowData) input).getImage());
                    favButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            favouriteButtonListenner(input,favButton);
                        }
                    });
                }
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClickListenner(parent, view, position, id);
            }
        });
    }

    private ArrayList<RowData> getSubListByName(String name){
        if (name.length()==0)return list;
        ArrayList<RowData> result = new ArrayList<>();
        for (RowData item:list){
            if(item.getText().toLowerCase().contains(name.toLowerCase()))result.add(item);
        }
        return result;
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public abstract void itemClickListenner(AdapterView<?> parent, View view, int position, long id);

    public abstract void favouriteButtonListenner(Object input, ImageButton favButton);

    public abstract int setThisContentView();

    protected abstract ArrayList<RowData> setList();
}
