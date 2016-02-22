package Controller;

import java.util.ArrayList;

import dataRecolectors.MockRecollector;
import model.SubTopic;
import model.Topic;

/**
 * Created by Carlos on 13/02/2016.
 */
public class AppController {

    private static AppController instance;
    private ArrayList<Topic> data = new ArrayList<>();
    private Topic topicSelected;

    protected AppController(){
        data = new MockRecollector().getData();
    }

    public static AppController getInstance(){
        if(instance==null)instance= new AppController();
        return instance;
    }

    public ArrayList<Topic> getData() {return data;}

    public SubTopic getSubDataFromName(String name){
        for (Topic topic :data ){
            for (SubTopic subTopic : topic.getSubTopics()){
                if (subTopic.getSubTopicName().equals(name))return subTopic;
            }
        }
        return null;
    }

    public ArrayList<SubTopic> getSubTopicList(){
        for (Topic topic :data ){
            if (topic.getTopicName().equals(topicSelected.getTopicName()))return topic.getSubTopics();
        }
        return null;
    }

    public void setTopicSelected(Topic item) {
        topicSelected =item;}

    public Topic getTopicSelected(){return topicSelected;}

}
