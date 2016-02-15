package app.com.example.carlos.tfgipol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import adapters.ListAdapter;
import dataRecolectors.MockRecolector;
import model.rowData;

public class TopicsView extends AppCompatActivity {

    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_topics_view);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loadList();
    }

    private void loadList() {
        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdapter(this, R.layout.text_and_button_row, MockRecolector.getInstance().getData()) {
            @Override
            public void input(final Object input, View view) {
                final ImageButton favButton = (ImageButton) view.findViewById(R.id.rowFavButton);
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText(((rowData) input).getName());
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
    }
}
