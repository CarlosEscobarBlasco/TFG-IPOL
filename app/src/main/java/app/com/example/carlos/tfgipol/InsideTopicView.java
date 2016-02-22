package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import dataRecolectors.MockRecolector;
import model.SubTopicData;

/**
 * Created by Carlos on 21/02/2016.
 */
public class InsideTopicView extends MyActivity {

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id) {
        ((SubTopicData)parent.getItemAtPosition(position)).addToHistory();
        Intent intent = new Intent(this, ArticleView.class);
        startActivity(intent);
    }

    @Override
    public void favouriteButtonListener(Object input, ImageButton favButton) {
        ((SubTopicData) input).addToFavourites();
        favButton.setImageResource(((SubTopicData) input).getImage());
    }

    @Override
    public String setListTitle() {
        return "Topics ->";
    }

    @Override
    protected String setListSubTitle() {
        return MockRecolector.getInstance().getTopicSelected().getTopic();
    }

    @Override
    protected ArrayList<SubTopicData> setList() {return MockRecolector.getInstance().getSubTopicList();}

    @Override
    public void goToTopic(View v) {
        Intent intent = new Intent(InsideTopicView.this, TopicsView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
