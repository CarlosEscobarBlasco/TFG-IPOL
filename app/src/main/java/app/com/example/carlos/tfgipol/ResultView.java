package app.com.example.carlos.tfgipol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import adapters.MyListAdapter;
import internetConexion.URLImageCollector;
import internetConexion.URLSendData;
import model.AppController;
import model.listElements.TopicElement;
import model.lists.Topics;

public class ResultView extends AppCompatActivity {
    private ProgressDialog progress;
    private ListView mainListView;
    private ArrayList<Bitmap> list = new ArrayList<>();
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
                System.out.println("carga la lista");
                mainListView.setAdapter(new MyListAdapter(activity, R.layout.image_row, list) {
                    @Override
                    public void input(final Object input, final View view) {
                        if (input != null) {
                            ImageView image = (ImageView) view.findViewById(R.id.resultImage);
                            image.setImageBitmap((Bitmap) input);
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
        final URLImageCollector imageCollector = new URLImageCollector("http://dev.ipol.im/~asalgado/ipol_demo_interpreter/"+AppController.getInstance().getDemoName()+"/tmp/"+ AppController.getInstance().getKey()+"/"+name+"_thumbnail.png","demo","demo");
        try {
            imageCollector.execute().get();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("añade el bitmap a la lista");
                    list.add(imageCollector.getBitmapFromURL());
                    /*ImageView imageView = (ImageView) findViewById(R.id.resultImage);
                    imageView.setImageBitmap(imageCollector.getBitmapFromURL());*/
                }
            });

        } catch (InterruptedException | ExecutionException e) {e.printStackTrace();}
    }

    private void showProgressDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress = new ProgressDialog(ResultView.this);
                progress.setTitle("Running");
                progress.setMessage("The algorithm is running. After 30s or less, you will get the results");
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
            setResults("denoised");
            setResults("input_0_sel");
            setResults("img_diff");
            setResults("noisy");
            onRestart();
            stopProgressDialog();
        }
    }

}
