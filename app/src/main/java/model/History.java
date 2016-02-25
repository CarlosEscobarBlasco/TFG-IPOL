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

import Controller.AppController;

/**
 * Created by Carlos on 15/02/2016.
 */
public class History {

    private static History instance = null;
    private ArrayList<SubTopicElement> history;
    private Context context = null;
    private static final int HISTORY_SIZE = 4;
    private static final String ITEM_SEPARATOR = ",";

    protected History() {
        this.history = new ArrayList<>();
    }

    public static History getInstance(){
        if(instance == null) instance = new History();
        return instance;
    }

    public void addToHistory(SubTopicElement item){
        if (history.contains(item))history.remove(item);
        if(history.size()>=HISTORY_SIZE) history.remove(history.size()-1);
        history.add(0,item);
        storeHistory();
    }
    public ArrayList<SubTopicElement> getHistory(){
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
            if(data.length()>0) AppController.getInstance().getSubDataFromName(data).addToHistory();
        }
    }

    private String transformHistoryToString() {
        String result="";
        for (SubTopicElement item : history) {
            result += item.getSubTopicName()+ITEM_SEPARATOR;
        }
        return result;
    }

}
