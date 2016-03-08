package dataCollector;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import model.listElements.SubTopicElement;
import model.listElements.TopicElement;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Carlos on 24/02/2016.
 */
public class HTMLTopicsCollector {
    private OkHttpClient client = new OkHttpClient();
    private ArrayList<TopicElement> data = new ArrayList<>();
    private String htmlResponse="";
    private boolean flag;

    public HTMLTopicsCollector() {
        flag=false;
        getHTML();
    }

    private void getHTML() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    htmlResponse=run("http://www.ipol.im/");
                    processHTML();
                } catch (Exception ignored) {}
                return null;
            }
        }.execute();
    }

    private void processHTML() {
        htmlResponse=htmlResponse.substring(htmlResponse.indexOf("Topics"),htmlResponse.lastIndexOf("Topics"));

        String[]pageInLInes=htmlResponse.split("[\\r\\n]+");
        ArrayList<String>result=new ArrayList<>();
        String topic;
        for (int i = 0; i < pageInLInes.length; i++) {
            if(pageInLInes[i].contains("</a></h2>")){
                pageInLInes[i]=pageInLInes[i].substring(0,pageInLInes[i].lastIndexOf("</a></h2>"));
                topic=pageInLInes[i].substring(pageInLInes[i].lastIndexOf(">")+1);
                i++;
                while (!pageInLInes[i].contains("</a></h2>")){
                    if(pageInLInes[i].contains("</a><br />")) {
                        pageInLInes[i]=pageInLInes[i].substring(0,pageInLInes[i].lastIndexOf("</a><br />"));
                        topic+=","+pageInLInes[i].substring(pageInLInes[i].lastIndexOf(">")+1);
                    }
                    if(i+1<pageInLInes.length)i++;
                    else break;
                }
                i--;
                result.add(topic);
            }
        }
        for (String a:result){
            createTopic(a.split(",")[0],a.substring(a.indexOf(",")+1).split(","));
        }
        flag=true;
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private void createTopic(String title, String[] subTopics){
        ArrayList<SubTopicElement> aux = new ArrayList<>();
        for (String description: subTopics){
            aux.add(new SubTopicElement(description));
        }
        data.add(new TopicElement(title, aux));
    }

    public ArrayList<TopicElement> getData() {
        while (!flag){
            System.out.print("");
        }
        return data;
    }

    /*public boolean isDataProcesed(){
        return flag;
    }*/
}
