package app.com.example.carlos.tfgipol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.MyListAdapter;
import model.SubTopicElement;

public class ArticleView extends AppCompatActivity {

    private ListView mainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        loadList();
    }

    private void loadList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Input 1");
        list.add("");
        list.add("Input 3");
        list.add("Input 4");
        list.add("Input 5");
        list.add("");
        mainListView = (ListView) findViewById(R.id.inputList);
        mainListView.setAdapter(new MyListAdapter(this, R.layout.input_row,list) {
            @Override
            public void input(Object input, View view) {
                TextView rowTextView = (TextView) view.findViewById(R.id.input_row_text);
                rowTextView.setText(((String) input));
                LinearLayout layout = (LinearLayout) view.findViewById(R.id.input_row_layout);
                if(((String) input).length()>0){
                    SeekBar seekBar = new SeekBar(getApplicationContext());
                    seekBar.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    layout.addView(seekBar);
                }else{
                    Button button = new Button(getApplicationContext());
                    layout.addView(button);
                    CheckBox checkBox = new CheckBox(getApplicationContext());
                    layout.addView(checkBox);
                }
            }
        });
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
