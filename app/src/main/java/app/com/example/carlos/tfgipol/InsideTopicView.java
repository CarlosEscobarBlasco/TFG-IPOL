package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;

import model.listElements.SubTopicElement;
import model.lists.Topics;

/**
 * Created by Carlos on 21/02/2016.
 */
public class InsideTopicView extends MyListActivity {

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id) {
        ((SubTopicElement)parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListener(Object input, MaterialFavoriteButton favButton) {
        favButton.setFavorite(!((SubTopicElement) input).getFavouriteStatus());
        ((SubTopicElement) input).addToFavourites();
    }

    @Override
    public String setListTitle() {
        return "Topics ->";
    }

    @Override
    protected String setListSubTitle() {
        return Topics.getInstance().getTopicSelected().getTopicName();
    }

    @Override
    protected ArrayList<SubTopicElement> setList() {return Topics.getInstance().getSubTopicListFromTopic(Topics.getInstance().getTopicSelected());}

    @Override
    public void goToTopicView(View v) {
        Intent intent = new Intent(InsideTopicView.this, TopicsView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
