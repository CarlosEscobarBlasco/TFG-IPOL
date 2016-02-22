package model;

import java.util.ArrayList;

/**
 * Created by Carlos on 21/02/2016.
 */
public class Topic {
    private String topicName;
    private ArrayList<SubTopic> subTopics;

    public Topic(String topicName, ArrayList<SubTopic> subTopics) {
        this.subTopics = subTopics;
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public ArrayList<SubTopic> getSubTopics() {
        return subTopics;
    }
}
