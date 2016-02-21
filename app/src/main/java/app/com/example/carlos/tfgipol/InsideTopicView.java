package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

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
    public String getText() {
        return "Topics ->";
    }

    @Override
    protected ArrayList<SubTopicData> setList() {return MockRecolector.getInstance().getSubTopicList();}
}
