package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import java.util.ArrayList;

import model.Favourites;
import model.SubTopic;

public class FavsView extends MyActivity {

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id) {
        ((SubTopic) parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(FavsView.this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListener(Object input, ImageButton favButton) {
        ((SubTopic) input).removeFromFavourites();
        loadList();
    }

    @Override
    public String setListTitle() { return "Favourites"; }

    @Override
    protected String setListSubTitle() {
        return "";
    }

    @Override
    protected ArrayList<SubTopic> setList() {
        return Favourites.getInstance().getFavourites();
    }

    @Override
    public void goToTopicView(View v) {
        //Do Nothing
    }

}
