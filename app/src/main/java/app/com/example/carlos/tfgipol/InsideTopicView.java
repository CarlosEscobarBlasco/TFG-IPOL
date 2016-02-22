package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import java.util.ArrayList;

import Controller.AppController;
import model.SubTopic;

/**
 * Created by Carlos on 21/02/2016.
 */
public class InsideTopicView extends MyActivity {

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id) {
        ((SubTopic)parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListener(Object input, ImageButton favButton) {
        ((SubTopic) input).addToFavourites();
        favButton.setImageResource(((SubTopic) input).getFavouriteStarImage());
    }

    @Override
    public String setListTitle() {
        return "Topics ->";
    }

    @Override
    protected String setListSubTitle() {
        return AppController.getInstance().getTopicSelected().getTopicName();
    }

    @Override
    protected ArrayList<SubTopic> setList() {return AppController.getInstance().getSubTopicList();}

    @Override
    public void goToTopicView(View v) {
        Intent intent = new Intent(InsideTopicView.this, TopicsView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
