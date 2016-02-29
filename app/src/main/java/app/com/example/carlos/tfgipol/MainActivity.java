package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

import adapters.MyListAdapter;
import model.Favourites;
import model.History;
import model.SubTopicElement;
import model.Topics;

public class MainActivity extends AppCompatActivity {

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
        ArrayList<SubTopicElement> items = History.getInstance().getHistory();
        if(items.size()>0){
            TwoWayView historyList = (TwoWayView) findViewById(R.id.lvItems);
            historyList.setAdapter(new MyListAdapter(this, R.layout.button_row, items) {
                @Override
                public void input(final Object input, View view) {
                    final Button button = (Button) view.findViewById(R.id.buttonRowButton);
                    if (input != null) {
                        String text = ((SubTopicElement) input).getSubTopicName();
                        text = text.length() > 50 ? text.substring(0, 47) + "..." : text;
                        button.setText(text);
                    }
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (input == null) return;
                            ((SubTopicElement) input).addToHistory();
                            Intent intent = new Intent(MainActivity.this, ArticleView.class);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }

    private void activateTopicsButton() {
        Button topicButton = (Button) findViewById(R.id.topicButton);
        topicButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopicsView.class);
                startActivity(intent);
            }
        });
    }

    private void activateFavsButton() {
        Button favsButton = (Button) findViewById(R.id.favsButton);
        favsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavsView.class);
                startActivity(intent);
            }
        });
    }

    private void startLists() {
        Topics.getInstance().startTopicList(getApplicationContext());
        History.getInstance().startHistoryList(getApplicationContext());
        Favourites.getInstance().startFavouritesList(getApplicationContext());
    }
}
