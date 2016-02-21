package model;

import java.util.ArrayList;

/**
 * Created by Carlos on 21/02/2016.
 */
public class TopicData {
    private String topic;
    private ArrayList<SubTopicData> data;

    public TopicData(String topic,ArrayList<SubTopicData> data) {
        this.data = data;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public ArrayList<SubTopicData> getData() {
        return data;
    }
}
