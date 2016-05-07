package app.com.example.carlos.tfgipol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import adapters.MyListAdapter;
import internetConexion.URLImageCollector;
import internetConexion.URLSendData;
import model.AppController;

public class ResultView extends AppCompatActivity {
    private ProgressDialog progress;
    private ListView mainListView;
    private ArrayList<ListElement> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        new Thread(new MyThread()).start();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        loadList(this);
    }

    protected void loadList(final AppCompatActivity activity) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainListView = (ListView) findViewById(R.id.list);
                mainListView.setAdapter(new MyListAdapter(activity, R.layout.image_row, list) {
                    @Override
                    public void input(final Object input, final View view) {
                        if (input != null) {
                            TextView text = (TextView) view.findViewById(R.id.resultText);
                            text.setText(((ListElement) input).getName());

                            ImageView image = (ImageView) view.findViewById(R.id.resultImage);
                            image.setImageBitmap(((ListElement) input).getBitmap());

                            TextView url = (TextView) view.findViewById(R.id.resultUrl);
                            url.setText("Open full image");

                            url.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((ListElement) input).getUrl()));
                                    startActivity(browserIntent);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void sendData() {
        AppController appController = AppController.getInstance();
        URLSendData urlSendData = new URLSendData(appController.getDemoName(),"parameters");
        try {
            appController.setKey(urlSendData.execute().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void setResults(String name) {
        String url = "dev.ipol.im/~asalgado/ipol_demo_interpreter/"+AppController.getInstance().getDemoName()+"/tmp/"+ AppController.getInstance().getKey()+"/"+name;
        final URLImageCollector imageCollector = new URLImageCollector("http://"+url+"_thumbnail.png","demo","demo");
        try {
            imageCollector.execute().get();
            char[] charArray = name.toCharArray();
            charArray[0]=Character.toUpperCase(charArray[0]);
            list.add(new ListElement(imageCollector.getBitmapFromURL(),new String(charArray),"http://demo:demo@"+url+".png"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void showProgressDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress = new ProgressDialog(ResultView.this);
                progress.setTitle("Running");
                progress.setMessage("The algorithm is running. After 30s or less, you will get the results");
                progress.setCanceledOnTouchOutside(false);
                progress.show();
            }
        });
    }

    private void stopProgressDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.dismiss();
            }
        });
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private class MyThread implements Runnable{
        @Override
        public void run() {
            showProgressDialog();
            sendData();
            for (String name :AppController.getInstance().getResultNames()) {
                setResults(name.toLowerCase());
            }
            onRestart();
            stopProgressDialog();
        }
    }

    private class ListElement{
        private Bitmap bitmap;
        private String name;
        private String url;

        public ListElement(Bitmap bitmap, String name, String url) {
            this.bitmap = bitmap;
            this.name = name;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public String getName() {
            return name;
        }

    }
}
