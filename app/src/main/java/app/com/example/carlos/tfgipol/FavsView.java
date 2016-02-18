package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import java.util.ArrayList;

import model.Favourites;
import model.RowData;

public class FavsView extends MyActivity {

    @Override
    public void itemClickListenner(AdapterView<?> parent, View view, int position, long id) {
        ((RowData) parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(FavsView.this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListenner(Object input, ImageButton favButton) {
        ((RowData) input).removeFromFavourites();
        loadList();
    }

    @Override
    public int setThisContentView() {
        return R.layout.activity_favs_view;
    }

    @Override
    protected ArrayList<RowData> setList() {
        return Favourites.getInstance().getFavourites();
    }

}
