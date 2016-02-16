package model;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Carlos on 15/02/2016.
 */
public class History {

    private static History instance = null;
    private ArrayList<rowData> history;
    private Context context = null;
    private static final int HISTORY_SIZE = 5;

    protected History() {
        this.history = new ArrayList<>();
    }

    public static History getInstance(){
        if(instance == null) instance = new History();
        return instance;
    }

    public void addToHistory(rowData item){
        if (history.contains(item))return;
        if(history.size()<HISTORY_SIZE)history.add(item);
        else{
            history.remove(0);
            history.add(item);
        }
        saveHistory();
    }

    private void saveHistory() {
        /*String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            System.out.println(context.getExternalFilesDir(null));
            File file = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "albumName");
            if (!file.mkdirs()) {
                System.out.println("Directory not created");
            }
        }else System.out.println("no se permite escribir un carajo");*/
    }

    public ArrayList<rowData> getHistory(){return history;}

    public void setContext(Context context){this.context=context;}

}
