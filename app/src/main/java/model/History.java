package model;

import java.util.ArrayList;

/**
 * Created by Carlos on 15/02/2016.
 */
public class History {

    private static History instance = null;
    private ArrayList<rowData> favourites;

    protected History() {
        this.favourites = new ArrayList<>();
    }

    public static History getInstance(){
        if(instance == null) instance = new History();
        return instance;
    }


}
