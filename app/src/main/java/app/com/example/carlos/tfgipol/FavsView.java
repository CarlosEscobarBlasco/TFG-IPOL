package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;

import model.lists.Favourites;
import model.listElements.SubTopicElement;

public class FavsView extends MyListActivity {

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id) {
        ((SubTopicElement) parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(FavsView.this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListener(Object input, Button favButton) {
        ((SubTopicElement) input).removeFromFavourites();
        loadList();
    }

    @Override
    public String setListTitle() { return "Favourites"; }

    @Override
    protected String setListSubTitle() {
        return "";
    }

    @Override
    protected ArrayList<SubTopicElement> setList() {
        return Favourites.getInstance().getFavourites();
    }

    @Override
    public void goToTopicView(View v) {
        //Do Nothing
    }

}
