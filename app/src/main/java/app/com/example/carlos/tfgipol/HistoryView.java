package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import adapters.ListAdapter;
import dataRecolectors.MockRecolector;
import model.History;
import model.rowData;

public class HistoryView extends AppCompatActivity {

    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);

        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdapter(this, R.layout.text_and_button_row, History.getInstance().getHistory()) {
            @Override
            public void input(final Object input, View view) {
                final ImageButton favButton = (ImageButton) view.findViewById(R.id.rowFavButton);
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText(((rowData) input).getText());
                    favButton.setImageResource(((rowData) input).getImage());
                    favButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((rowData) input).addToFavourites();
                            favButton.setImageResource(((rowData) input).getImage());
                        }
                    });
                }
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((rowData) parent.getItemAtPosition(position)).addToHistory();
                Intent intent = new Intent(HistoryView.this, ArticleView.class);
                startActivity(intent);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
