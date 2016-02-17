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

/**
 * Created by Carlos on 15/02/2016.
 */
public class History {

    private static History instance = null;
    private ArrayList<RowData> history;
    private Context context = null;
    private static final int HISTORY_SIZE = 5;
    private static final String ROW_DATA_SEPARATOR = ",";
    private static final String ITEMS_IN_ROW_DATA_SEPARATOR = "%";

    protected History() {
        this.history = new ArrayList<>();
    }

    public static History getInstance(){
        if(instance == null) instance = new History();
        return instance;
    }

    public void addToHistory(RowData item){
        if (history.contains(item))return;
        if(history.size()>=HISTORY_SIZE) history.remove(0);
        history.add(item);
        saveHistory(item);
    }

    private void saveHistory(RowData item) {
        BufferedWriter bufferedWriter = null;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("historyData",Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(transformHistoryToString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String transformHistoryToString() {
        String result="";
        for (RowData item : history) {
            result += item.getText()+ITEMS_IN_ROW_DATA_SEPARATOR+item.getImage()+ROW_DATA_SEPARATOR;
        }
        return result;
    }

    private void loadHistory(){
        BufferedReader bufferedReader = null;
        String result="";
        try {
            context.openFileOutput("historyData",Context.MODE_APPEND);
            FileInputStream fileInputStream = context.openFileInput("historyData");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line=bufferedReader.readLine())!=null) result+=(line);
            history = transformStringToHistory(result);
        } catch (IOException ignored){}
        finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();}
            catch (IOException ignored) {}
        }
    }

    private ArrayList<RowData> transformStringToHistory(String input) {
        ArrayList<RowData> result = new ArrayList<>();
        if (input.length()==0)return result;
        for (String data:input.split(ROW_DATA_SEPARATOR)){
            if(data.length()>0)result.add(new RowData(data.split(ITEMS_IN_ROW_DATA_SEPARATOR)[0],Integer.parseInt(data.split("%")[1])));
        }
        return result;
    }

    public ArrayList<RowData> getHistory(){
        if (history.size()==0)loadHistory();
        return history;
    }

    public void setContext(Context context){this.context=context;}

}
