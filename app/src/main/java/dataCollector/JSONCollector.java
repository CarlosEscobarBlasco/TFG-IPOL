package dataCollector;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Carlos on 17/03/2016.
 */
public class JSONCollector extends AsyncTask<String, String, String> {


    private HttpURLConnection urlConnection;
    private String urlDirection;

    public JSONCollector(String urlDirection) {
        this.urlDirection = urlDirection;
    }

    @Override
    protected String doInBackground(String... args) {
        String result = "";
        try {
            URL url = new URL(urlDirection);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {result +=line;}
        }catch( Exception ignored) {
        }finally {
            urlConnection.disconnect();
        }
        return result;
    }

}
