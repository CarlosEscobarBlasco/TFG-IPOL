package internetConexion;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import model.AppController;

/**
 * Created by Carlos on 10/05/2016.
 */
public class SendImage extends AsyncTask<String, String, String> {
    private HttpURLConnection urlConnection;
    private String urlDirection;
    private String bitmap;

    public SendImage(String demoID) {
        bitmap=bitmapToString(AppController.getInstance().getSelectedImage());
        createURL(demoID);
    }

    public String bitmapToString(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String result = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        while (result.length()%4!=0)result+="=";
        return result;
    }

    private void createURL(String demoID) {
        this.urlDirection ="http://dev.ipol.im/~asalgado/ipol_demo_interpreter/"+demoID+"/mobile_upload";
    }

    @Override
    protected String doInBackground(String... args) {
        String result = "";
        try {
            URL url = new URL(urlDirection);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Authorization", "Basic " + Base64.encodeToString("demo:demo".getBytes(), Base64.NO_WRAP));


            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("sigma",AppController.getInstance().getParam())
                    .appendQueryParameter("file_0", bitmap);

            String query = builder.build().getEncodedQuery();
            System.out.println(query);
            OutputStream os = urlConnection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();


            urlConnection.connect();
            System.out.println(urlConnection.getResponseCode());
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result +=line;
            }
        }catch( Exception ignored) {
            System.out.println(ignored);
        }finally {
            urlConnection.disconnect();
        }
        return result;
    }
}