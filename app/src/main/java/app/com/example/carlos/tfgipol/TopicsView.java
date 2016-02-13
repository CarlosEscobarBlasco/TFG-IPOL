package app.com.example.carlos.tfgipol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import adapters.ListAdaptor;
import dataRecolectors.DataRecolector;
import dataRecolectors.MockRecolector;
import model.Favourites;

public class TopicsView extends AppCompatActivity {

    private ListView mainListView;
    private DataRecolector recolector = new MockRecolector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_topics_view);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loadList();
    }

    private void loadList() {
        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdaptor(this, R.layout.text_and_button_row, recolector.getData()) {
            @Override
            public void input(final Object input, View view) {
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText((String) input);
                }
                final ImageButton favouriteButton = (ImageButton) view.findViewById(R.id.rowFavButton);
                favouriteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favouriteButton.setImageResource(R.drawable.full_star);
                        Favourites.getInstance().addFavourite((String)input);
                    }
                });
            }
        });

    }
}
