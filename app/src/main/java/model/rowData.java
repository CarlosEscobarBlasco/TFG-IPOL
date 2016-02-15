package model;

import app.com.example.carlos.tfgipol.R;

/**
 * Created by Carlos on 15/02/2016.
 */
public class rowData {
    private String name;
    private int image;

    public rowData(String name) {
        this.name = name;
        this.image= R.drawable.empty_star;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
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

    }

    public void removeFromHistory(){

    }
}
