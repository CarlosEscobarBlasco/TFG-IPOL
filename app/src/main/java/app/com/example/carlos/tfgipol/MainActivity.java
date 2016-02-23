package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

import adapters.ListAdapter;
import model.Favourites;
import model.History;
import model.SubTopic;

public class MainActivity extends AppCompatActivity {

    Button favsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLists();
        activateButtons();
    }

    @Override
    protected void onResume(){
        super.onResume();
        createHorizontalViewList();
    }

    private void activateButtons() {

        activateFavsButton();
        createHorizontalViewList();
        activateTopicsButton();
    }

    private void createHorizontalViewList() {
        ArrayList<SubTopic> items = History.getInstance().getHistory();
        if(items.size()>0){
            TwoWayView historyList = (TwoWayView) findViewById(R.id.lvItems);
            historyList.setAdapter(new ListAdapter(this, R.layout.button_row, items) {
                @Override
                public void input(final Object input, View view) {
                    final Button button = (Button) view.findViewById(R.id.buttonRowButton);
                    if (input != null) {
                        String text = ((SubTopic) input).getSubTopicName();
                        text = text.length() > 50 ? text.substring(0, 47) + "..." : text;
                        button.setText(text);
                    }
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (input == null) return;
                            ((SubTopic) input).addToHistory();
                            Intent intent = new Intent(MainActivity.this, ArticleView.class);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
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

    private void startLists() {
        History.getInstance().startHistoryList(getApplicationContext());
        Favourites.getInstance().startFavouritesList(getApplicationContext());
    }
}
