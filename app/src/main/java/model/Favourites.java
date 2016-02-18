package model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import dataRecolectors.MockRecolector;

/**
 * Created by Carlos on 13/02/2016.
 */
public class Favourites {
    private static Favourites instance = null;
    private ArrayList<RowData> favourites;
    private Context context;
    private static final String ITEM_SEPARATOR = ",";

    protected Favourites() {
        this.favourites = new ArrayList<>();
    }

    public static Favourites getInstance(){
        if(instance == null) instance = new Favourites();
        return instance;
    }

    public boolean addFavourite(RowData item){
        if (favourites.contains(item)){
            favourites.remove(item);
            storeFavourites();
            return false;
        }else{
            favourites.add(item);
            storeFavourites();
            return true;
        }
    }

    public void removeFavourite(RowData item){
        favourites.remove(item);
        storeFavourites();
    }

    public ArrayList<RowData> getFavourites(){

        return favourites;
    }

    public void startFavouritesList(Context context){
        this.context=context;
        if (favourites.size()==0)loadFavourites();
    }

    private void storeFavourites(){
        BufferedWriter bufferedWriter = null;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("favouritesData", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(transformFavouritesToString());
        } catch (IOException ignored) {
        }finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException ignored) {}
        }
    }

    private void loadFavourites(){
        BufferedReader bufferedReader = null;
        String result="";
        try {
            context.openFileOutput("favouritesData",Context.MODE_APPEND);
            FileInputStream fileInputStream = context.openFileInput("favouritesData");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line=bufferedReader.readLine())!=null) result+=(line);
            addStringToFavourites(result);
        } catch (IOException ignored){}
        finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();}
            catch (IOException ignored) {}
        }
    }

    private void addStringToFavourites(String input) {
        if (input.length()==0)return;
        for (String data:input.split(ITEM_SEPARATOR)){
            if(data.length()>0)MockRecolector.getInstance().getObjectByName(data).addToFavourites();
        }
    }

    private String transformFavouritesToString() {
        String result="";
        for (RowData item : favourites) {
            result += item.getText()+ITEM_SEPARATOR;
        }
        return result;
    }
}
