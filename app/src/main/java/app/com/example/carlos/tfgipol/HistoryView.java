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

import java.util.ArrayList;

import adapters.ListAdapter;
import model.History;
import model.RowData;

public class HistoryView extends MyActivity {

    @Override
    public void itemClickListenner(AdapterView<?> parent, View view, int position, long id) {
        ((RowData) parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(HistoryView.this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListenner(Object input, ImageButton favButton) {
        ((RowData) input).addToFavourites();
        favButton.setImageResource(((RowData) input).getImage());
    }

    @Override
    public int setThisContentView() {
        return R.layout.activity_history_view;
    }

    @Override
    protected ArrayList<RowData> setList() {
        return History.getInstance().getHistory();
    }

}
