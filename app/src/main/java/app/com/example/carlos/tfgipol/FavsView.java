package app.com.example.carlos.tfgipol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import adapters.ListAdapter;

import model.Favourites;
import model.RowData;

public class FavsView extends Activity {

    private ListView mainListView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs_view);
        loadList(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void loadList(final Bundle savedInstanceState) {
        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdapter(this, R.layout.text_and_button_row, Favourites.getInstance().getFavourites()) {
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
                            ((RowData) input).removeFromFavourites();
                            onCreate(savedInstanceState);
                        }
                    });
                }
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((RowData) parent.getItemAtPosition(position)).addToHistory();
                Intent intent = new Intent(FavsView.this, ArticleView.class);
                startActivity(intent);
            }
        });
    }
}
