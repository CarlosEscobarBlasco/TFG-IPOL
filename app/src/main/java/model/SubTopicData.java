package model;

import app.com.example.carlos.tfgipol.R;

/**
 * Created by Carlos on 15/02/2016.
 */
public class SubTopicData {
    private String text;
    private int image;

    public SubTopicData(String name) {
        this.text = name;
        this.image= R.drawable.empty_star;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void addToFavourites(){
        if(Favourites.getInstance().addFavourite(this))image = R.drawable.full_star;
        else image = R.drawable.empty_star;
    }

    public void removeFromFavourites(){
        Favourites.getInstance().removeFavourite(this);
        image = R.drawable.empty_star;
    }

    public void addToHistory(){
        History.getInstance().addToHistory(this);
    }

}
