package model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.MyListAdapter;
import app.com.example.carlos.tfgipol.R;

/**
 * Created by Carlos on 05/03/2016.
 */
public class RowParametersBuilder {
    Context applicationContext;
    MyListAdapter myListAdapter;

    public RowParametersBuilder(Context applicationContext, MyListAdapter myListAdapter) {
        this.applicationContext = applicationContext;
        this.myListAdapter=myListAdapter;
    }

    public void createRow(JSONObject input, View view) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.input_row_layout);
        TextView rowTextView = (TextView) view.findViewById(R.id.input_row_text);
        try {
            if (rowTextView.getText().equals(setText(input)))return;
            rowTextView.setText(setText(input));
            layout.addView(setInputComponent(input));
        } catch (JSONException ignored) {
        }
    }

    private String setText(JSONObject input) throws JSONException {
        return input.get("label").toString();
    }

    private View setInputComponent(JSONObject input)throws JSONException {
        if (input.has("type_format")){
            switch (input.get("type_format").toString()){
                case "selection_collapsed": return createSpinner(input);
                case "text_slider": return createSlider(input);
            }
        }else {
            return createPlainText(input);
        }
        return null;
    }

    private View createSlider(JSONObject input) {
        SeekBar seekBar = new SeekBar(applicationContext);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(200,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return seekBar;
    }

    private View createPlainText(JSONObject input) {
        EditText text = new EditText(applicationContext);
        text.setLayoutParams(new LinearLayout.LayoutParams(100,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return text;
    }

    private View createSpinner(JSONObject input) {
        String[] arraySpinner = new String[] {"2", "5", "10", "15", "20", "25", "30", "35", "40"};
        Spinner spinner = new Spinner(applicationContext);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(100,
                ViewGroup.LayoutParams.WRAP_CONTENT));
       spinner.setAdapter(new ArrayAdapter<>(applicationContext,android.R.layout.simple_spinner_item, arraySpinner));
        return spinner;
    }


}
