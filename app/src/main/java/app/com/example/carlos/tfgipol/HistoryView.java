package app.com.example.carlos.tfgipol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import adapters.ListAdaptor;
import dataRecolectors.DataRecolector;
import dataRecolectors.MockRecolector;

public class HistoryView extends AppCompatActivity {

    private ListView mainListView;
    private DataRecolector recolector = new MockRecolector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_history_view);

        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdaptor(this, R.layout.text_and_button_row, recolector.getData()) {
            @Override
            public void input(Object input, View view) {
                if (input != null) {
                    TextView rowTextView = (TextView) view.findViewById(R.id.rowTextView);
                    rowTextView.setText((String) input);
                }
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
