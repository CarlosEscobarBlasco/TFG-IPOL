package model.listElements;


import model.lists.Favourites;
import model.lists.History;

/**
 * Created by Carlos on 15/02/2016.
 */
public class SubTopicElement {
    private String subTopicName;
    private boolean favouriteStatus;

    public SubTopicElement(String name) {
        this.subTopicName = name;
        favouriteStatus = false;
    }

    public boolean getFavouriteStatus() {
        return favouriteStatus;
    }

    public String getSubTopicName() {
        return subTopicName;
    }

    public void addToFavourites(){
        favouriteStatus = Favourites.getInstance().addFavourite(this);
    }

    public void removeFromFavourites(){
        Favourites.getInstance().removeFavourite(this);
        favouriteStatus = false;
    }

    public void addToHistory(){
        History.getInstance().addToHistory(this);
    }

}
