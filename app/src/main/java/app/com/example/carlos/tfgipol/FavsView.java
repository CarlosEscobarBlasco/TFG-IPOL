package app.com.example.carlos.tfgipol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import adapters.ListAdaptor;

import model.Favourites;

public class FavsView extends Activity {

    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs_view);

        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(new ListAdaptor(this,R.layout.text_and_button_row, Favourites.getInstance().getFavourites()) {
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
