package internetConexion;

import android.os.AsyncTask;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.AppController;

/**
 * Created by Carlos on 26/04/2016.
 */
public class URLSendData extends AsyncTask<String, String, String> {
    private HttpURLConnection urlConnection;
    private String urlDirection;

    public URLSendData(String demoID,String... parameters) {
        createURL(demoID,parameters);
    }

    private void createURL(String demoID,String[] parameters) {
        this.urlDirection =
                "http://dev.ipol.im/~asalgado/ipol_demo_interpreter/"+demoID+"/mobile?id="+ AppController.getInstance().getSelectedExampleImageNumber()+"&sigma="+AppController.getInstance().getParam();
    }

    @Override
    protected String doInBackground(String... args) {
        String result = "";
        try {
                        System.out.println(urlDirection);
            URL url = new URL(urlDirection);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Basic " + Base64.encodeToString("demo:demo".getBytes(), Base64.NO_WRAP));
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result +=line;
            }
        }catch( Exception ignored) {
        }finally {
            urlConnection.disconnect();
        }
        return result;
    }
}
