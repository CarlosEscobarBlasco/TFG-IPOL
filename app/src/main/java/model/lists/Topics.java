package model.lists;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import internetConexion.HTMLTopicsCollector;
import model.listElements.SubTopicElement;
import model.listElements.TopicElement;

/**
 * Created by Carlos on 25/02/2016.
 */
public class Topics {
    private static final String TOPIC_SEPARATOR = ";;";
    private static final String SUB_TOPIC_SEPARATOR = ",";
    private static Topics instance = null;
    private ArrayList<TopicElement> topicList;
    private Context context;
    private TopicElement topicSelected;

    protected Topics() {
        this.topicList = new ArrayList<>();
    }

    public static Topics getInstance(){
        if(instance == null) instance = new Topics();
        return instance;
    }

    public void startTopicList(Context context){
        this.context=context;
        if (topicList.size()==0) loadTopicsFromLocal();
    }

    public ArrayList<TopicElement> getTopicList(){
        return topicList;
    }

    public void setTopicSelected(TopicElement item) {topicSelected =item;}

    public TopicElement getTopicSelected(){return topicSelected;}

    public ArrayList<SubTopicElement> getSubTopicListFromTopic(TopicElement topicSelected){
        for (TopicElement topicElement :topicList ){
            if (topicElement.getTopicName().equals(topicSelected.getTopicName()))return topicElement.getSubTopicElements();
        }
        return null;
    }

    public SubTopicElement getSubDataFromName(String name){
        for (TopicElement topicElement :topicList ){
            for (SubTopicElement subTopicElement : topicElement.getSubTopicElements()){
                if (subTopicElement.getSubTopicName().equals(name))return subTopicElement;
            }
        }
        return null;
    }

    private void loadTopicsFromLocal() {
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
        topicList = new HTMLTopicsCollector().getData();
        storeTopicsOnLocal();
    }

    private void storeTopicsOnLocal() {
        BufferedWriter bufferedWriter = null;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("topicsData", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(transformTopicsToString());
        } catch (IOException ignored) {
        }finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException ignored) {}
        }
    }

    private String transformTopicsToString() {
        String result="";
        for (TopicElement item : topicList) {
            for (SubTopicElement subItem:item.getSubTopicElements()){
                result += subItem.getSubTopicName()+SUB_TOPIC_SEPARATOR;
            }
            result += item.getTopicName()+TOPIC_SEPARATOR;
        }
        return result;
    }

    private void addStringToTopics(String result) {
        if (result.length()==0){
            loadTopicsFromHTML();
        }else{
            for (String topic : result.split(TOPIC_SEPARATOR)){
                String[] subTopic = topic.split(SUB_TOPIC_SEPARATOR);
                ArrayList<SubTopicElement> aux = new ArrayList<>();
                for (int i = 0; i < subTopic.length-1; i++) {
                    aux.add(new SubTopicElement(subTopic[i]));
                }
                topicList.add(new TopicElement(subTopic[subTopic.length - 1], aux));
            }
        }
    }
}
