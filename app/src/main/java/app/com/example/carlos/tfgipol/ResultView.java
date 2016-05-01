package app.com.example.carlos.tfgipol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

import internetConexion.URLImageCollector;
import internetConexion.URLSendData;
import model.AppController;

public class ResultView extends AppCompatActivity {
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        new Thread(new MyThread()).start();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    private void sendData() {
        //showProgressDialog();
        AppController appController = AppController.getInstance();
        URLSendData urlSendData = new URLSendData(appController.getDemoName(),"parameters");
        try {
            appController.setKey(urlSendData.execute().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void setResults() {
        final URLImageCollector imageCollector = new URLImageCollector("http://dev.ipol.im/~asalgado/ipol_demo_interpreter/"+AppController.getInstance().getDemoName()+"/tmp/"+ AppController.getInstance().getKey()+"/input_0_sel.png","demo","demo");
        try {
            imageCollector.execute().get();
            //stopProgressDialog();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ImageView imageView = (ImageView) findViewById(R.id.resultImage);
                    imageView.setImageBitmap(imageCollector.getBitmapFromURL());
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
            setResults();
            onRestart();
            stopProgressDialog();
        }
    }

}
