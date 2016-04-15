package dataCollector;

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

    public URLImageCollector(String url) {
        this.urlDirection = url;
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            URL url = new URL(urlDirection);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            bitmap = myBitmap;
            return myBitmap.toString();
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public Bitmap getBitmapFromURL() {
       return bitmap;
    }

}
