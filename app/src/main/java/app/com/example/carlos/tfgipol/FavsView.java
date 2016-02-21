package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import java.util.ArrayList;

import model.Favourites;
import model.SubTopicData;

public class FavsView extends MyActivity {

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id) {
        ((SubTopicData) parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(FavsView.this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListener(Object input, ImageButton favButton) {
        ((SubTopicData) input).removeFromFavourites();
        loadList();
    }

    @Override
    public String getText() { return "Favourites"; }

    @Override
    protected ArrayList<SubTopicData> setList() {
        return Favourites.getInstance().getFavourites();
    }

}
