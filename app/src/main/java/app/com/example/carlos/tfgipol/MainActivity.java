package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button favsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateButtons();
    }

    private void activateButtons() {

        activateFavsButton();
        activateHistoryButton();
        activateTopicsButton();
    }

    private void activateHistoryButton() {
        favsButton = (Button) findViewById(R.id.histButton);
        favsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryView.class);
                startActivity(intent);
            }
        });
    }

    private void activateTopicsButton() {
        favsButton = (Button) findViewById(R.id.topicButton);
        favsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopicsView.class);
                startActivity(intent);
            }
        });
    }

    private void activateFavsButton() {
        favsButton = (Button) findViewById(R.id.favsButton);
        favsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavsView.class);
                startActivity(intent);
            }
        });
    }
}
