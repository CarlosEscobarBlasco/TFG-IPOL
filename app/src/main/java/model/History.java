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
 * Created by Carlos on 15/02/2016.
 */
public class History {

    private static History instance = null;
    private ArrayList<RowData> history;
    private Context context = null;
    private static final int HISTORY_SIZE = 5;
    private static final String ITEM_SEPARATOR = ",";

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
        storeHistory();
    }
    public ArrayList<RowData> getHistory(){
        return history;
    }

    public void startHistoryList(Context context){
        this.context=context;
        if (history.size()==0)loadHistory();
    }

    private void storeHistory() {
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

    private void loadHistory(){
        BufferedReader bufferedReader = null;
        String result="";
        try {
            context.openFileOutput("historyData",Context.MODE_APPEND);
            FileInputStream fileInputStream = context.openFileInput("historyData");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line=bufferedReader.readLine())!=null) result+=(line);
            addStringToHistory(result);
        } catch (IOException ignored){}
        finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();}
            catch (IOException ignored) {}
        }
    }

    private void addStringToHistory(String input) {
        if (input.length()==0)return;
        for (String data:input.split(ITEM_SEPARATOR)){
            if(data.length()>0)MockRecolector.getInstance().getObjectByName(data).addToHistory();
        }
    }

    private String transformHistoryToString() {
        String result="";
        for (RowData item : history) {
            result += item.getText()+ITEM_SEPARATOR;
        }
        return result;
    }



}
