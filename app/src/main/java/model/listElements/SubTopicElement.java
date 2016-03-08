package model.listElements;

import app.com.example.carlos.tfgipol.R;
import model.lists.Favourites;
import model.lists.History;

/**
 * Created by Carlos on 15/02/2016.
 */
public class SubTopicElement {
    private String subTopicName;
    private int favouriteStarImage;

    public SubTopicElement(String name) {
        this.subTopicName = name;
        this.favouriteStarImage = R.drawable.empty_star;
    }

    public int getFavouriteStarImage() {
        return favouriteStarImage;
    }

    public String getSubTopicName() {
        return subTopicName;
    }

    public void addToFavourites(){
        if(Favourites.getInstance().addFavourite(this)) favouriteStarImage = R.drawable.full_star;
        else favouriteStarImage = R.drawable.empty_star;
    }

    public void removeFromFavourites(){
        Favourites.getInstance().removeFavourite(this);
        favouriteStarImage = R.drawable.empty_star;
    }

    public void addToHistory(){
        History.getInstance().addToHistory(this);
    }

}
