package dataRecolectors;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Carlos on 24/02/2016.
 */
public class HTMLRecollector {
    private OkHttpClient client = new OkHttpClient();
    //private final String TOPIC_FINDER = "";
    private String htmlResponse="";

    public HTMLRecollector() {
        getHTML();

    }

    private void getHTML() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    htmlResponse=run("http://www.ipol.im/");
                    topics();
                } catch (Exception ignored) {}
                return null;
            }

        }.execute();

    }

    private void topics() {
        System.out.println(htmlResponse);
        //ArrayList<String> result = new ArrayList<>();
        //if(htmlResponse.length()<0)return;
        //htmlResponse=htmlResponse.substring(htmlResponse.indexOf("Topics"),htmlResponse.lastIndexOf("Topics"));
        //String aux = htmlResponse;
        //System.out.println("La respuesta al primer topic es: "+htmlResponse.substring(htmlResponse.substring(htmlResponse.indexOf("</a></h2>")).lastIndexOf(">"),htmlResponse.indexOf("</a></h2>")));
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
