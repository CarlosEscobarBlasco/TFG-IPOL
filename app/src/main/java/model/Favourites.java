package model;

import java.util.ArrayList;

/**
 * Created by Carlos on 13/02/2016.
 */
public class Favourites {
    private static Favourites instance = null;
    private ArrayList<rowData> favourites;

    protected Favourites() {
        this.favourites = new ArrayList<>();
    }

    public static Favourites getInstance(){
        if(instance == null) instance = new Favourites();
        return instance;
    }

    public boolean addFavourite(rowData item){
        if (favourites.contains(item)){
            favourites.remove(item);
            return false;
        }else{
            favourites.add(item);
            return true;
        }
    }

    public void removeFavourite(rowData item){
        favourites.remove(item);
    }

    public ArrayList<rowData> getFavourites(){
        return favourites;
    }
}
