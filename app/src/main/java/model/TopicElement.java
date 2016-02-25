package model;

import java.util.ArrayList;

/**
 * Created by Carlos on 21/02/2016.
 */
public class TopicElement {
    private String topicName;
    private ArrayList<SubTopicElement> subTopicElements;

    public TopicElement(String topicName, ArrayList<SubTopicElement> subTopicElements) {
        this.subTopicElements = subTopicElements;
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public ArrayList<SubTopicElement> getSubTopicElements() {
        return subTopicElements;
    }
}
