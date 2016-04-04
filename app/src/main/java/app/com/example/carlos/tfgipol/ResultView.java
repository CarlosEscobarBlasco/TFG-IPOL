package app.com.example.carlos.tfgipol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResultView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final ProgressDialog progress = new ProgressDialog(ResultView.this);
        progress.setTitle("Running");
        progress.setMessage("The algorithm is running. After 30s or less, you will get the results");
        progress.show();
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
