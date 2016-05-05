package internetConexion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Carlos on 15/04/2016.
 */
public class URLImageCollector extends AsyncTask<String, String, String> {

    private String urlDirection;
    private Bitmap bitmap;
    private String user;
    private String password;

    public URLImageCollector(String url,String user, String password){
        this.urlDirection=url;
        this.user=user;
        this.password=password;
    }


    @Override
    protected String doInBackground(String... args) {
        try {
            System.out.println(urlDirection);
            URL url = new URL(urlDirection);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Basic " + Base64.encodeToString((user+":"+password).getBytes(), Base64.NO_WRAP));
            connection.setDoInput(true);
            connection.connect();
            System.out.println(connection.getResponseCode());
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            bitmap = myBitmap;
            return myBitmap.toString();
        } catch (IOException e) {
            e.printStackTrace();
            // Log exception
            return null;
        }
    }

    public Bitmap getBitmapFromURL() {
       return bitmap;
    }

}
