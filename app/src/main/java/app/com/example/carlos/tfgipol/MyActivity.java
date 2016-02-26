package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import adapters.ListAdapter;
import model.SubTopicElement;

/**
 * Created by Carlos on 18/02/2016.
 */
public abstract class MyActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayList<SubTopicElement> fullList = setList();
    private ArrayList<SubTopicElement> list = fullList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        loadList();
        loadSearchFilter();
        editTitle();
        editSubTitle();
    }

    private void editTitle() {
        TextView title = (TextView) findViewById(R.id.listTitle);
        title.setText(setListTitle());
    }

    private void editSubTitle(){
        TextView subTitle = (TextView) findViewById(R.id.listSubTitle);
        String text = setListSubTitle().length() > 20 ? setListSubTitle().substring(0, 17)+"...": setListSubTitle();

        subTitle.setText(text);
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
                final Button favButton = (Button) view.findViewById(R.id.rowFavButton);
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText(((SubTopicElement) input).getSubTopicName());
                    favButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,((SubTopicElement) input).getFavouriteStarImage());
                    favButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            favouriteButtonListener(input, favButton);
                        }
                    });
                }
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClickListener(parent, view, position, id);
            }
        });
    }

    private ArrayList<SubTopicElement> getSubListByName(String name){
        if (name.length()==0)return list;
        ArrayList<SubTopicElement> result = new ArrayList<>();
        for (SubTopicElement item:list){
            if(item.getSubTopicName().toLowerCase().contains(name.toLowerCase()))result.add(item);
        }
        return result;
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public abstract void itemClickListener(AdapterView<?> parent, View view, int position, long id);

    public abstract void favouriteButtonListener(Object input, Button favButton);

    public abstract String setListTitle();

    protected abstract String setListSubTitle();

    protected abstract ArrayList<SubTopicElement> setList();

    public abstract void goToTopicView(View v);

}
