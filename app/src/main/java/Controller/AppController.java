package controller;

import java.util.ArrayList;

import model.SubTopicElement;
import model.TopicElement;
import model.Topics;

/**
 * Created by Carlos on 13/02/2016.
 */
public class AppController {

    private static AppController instance;
    private ArrayList<TopicElement> data = new ArrayList<>();
    private TopicElement topicSelected;

    protected AppController(){
        data = Topics.getInstance().getTopics();
        System.out.println("la longitud de los topics en el appcontroller es de: "+ data.size());
    }

    public static AppController getInstance(){
        if(instance==null)instance= new AppController();
        return instance;
    }

    public ArrayList<TopicElement> getData() {return data;}

    public SubTopicElement getSubDataFromName(String name){
        for (TopicElement topicElement :data ){
            for (SubTopicElement subTopicElement : topicElement.getSubTopicElements()){
                if (subTopicElement.getSubTopicName().equals(name))return subTopicElement;
            }
        }
        return null;
    }

    public ArrayList<SubTopicElement> getSubTopicList(){
        for (TopicElement topicElement :data ){
            if (topicElement.getTopicName().equals(topicSelected.getTopicName()))return topicElement.getSubTopicElements();
        }
        return null;
    }

    public void setTopicSelected(TopicElement item) {
        topicSelected =item;}

    public TopicElement getTopicSelected(){return topicSelected;}

}
