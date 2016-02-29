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

import dataRecolectors.HTMLRecollector;

/**
 * Created by Carlos on 25/02/2016.
 */
public class Topics {
    private static Topics instance = null;
    private ArrayList<TopicElement> topics;
    private Context context;
    private static final String ITEM_SEPARATOR = ",";

    protected Topics() {
        this.topics = new ArrayList<>();
    }

    public static Topics getInstance(){
        if(instance == null) instance = new Topics();
        return instance;
    }

    public void startTopicList(Context context){
        this.context=context;
        if (topics.size()==0) loadTopicsFromLocal();
    }

    public ArrayList<TopicElement> getTopics(){
        return topics;
    }

    private void loadTopicsFromLocal() {
        System.out.println("load from local");
        BufferedReader bufferedReader = null;
        String result="";
        try {
            context.openFileOutput("topicsData",Context.MODE_APPEND);
            FileInputStream fileInputStream = context.openFileInput("topicsData");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line=bufferedReader.readLine())!=null) result+=(line);
            addStringToTopics(result);
        } catch (IOException ignored){}
        finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();}
            catch (IOException ignored) {}
        }
    }

    private void loadTopicsFromHTML() {
        System.out.println("load from HTML");
        topics = new HTMLRecollector().getData();
        storeTopicsOnLocal();
    }

    private void storeTopicsOnLocal() {
        System.out.println("Storing data.........");
        BufferedWriter bufferedWriter = null;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("topicsData", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(transformTopicsToString());
        } catch (IOException ignored) {
            System.out.println("Fail Storing :'(");
        }finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException ignored) {}
        }
        System.out.println("Data stored");
    }

    private String transformTopicsToString() {
        String result="";
        for (TopicElement item : topics) {
            for (SubTopicElement subItem:item.getSubTopicElements()){
                result += subItem.getSubTopicName()+",";
            }
            result += item.getTopicName()+";;";
        }
        return result;
    }

    private void addStringToTopics(String result) {
        if (result.length()==0){
            loadTopicsFromHTML();
        }else{
            for (String topic : result.split(";;")){
                String[] subTopic = topic.split(",");
                ArrayList<SubTopicElement> aux = new ArrayList<>();
                for (int i = 0; i < subTopic.length-1; i++) {
                    aux.add(new SubTopicElement(subTopic[i]));
                }
                topics.add(new TopicElement(subTopic[subTopic.length-1], aux));
            }
        }
    }
}
