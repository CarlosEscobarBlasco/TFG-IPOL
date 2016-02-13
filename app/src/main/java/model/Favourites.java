package model;

import java.util.ArrayList;

/**
 * Created by Carlos on 13/02/2016.
 */
public class Favourites {
    private static Favourites instance = null;
    private ArrayList<String> favourites;

    protected Favourites() {
        this.favourites = new ArrayList<>();
    }

    public static Favourites getInstance(){
        if(instance ==null) instance = new Favourites();
        return instance;
    }

    public void addFavourite(String item){
        favourites.add(item);
    }

    public void removeFavourite(String item){
        favourites.remove(item);
    }

    public ArrayList<String> getFavourites(){
        return favourites;
    }
}
