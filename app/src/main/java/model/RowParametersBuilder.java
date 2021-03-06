package model;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import adapters.MyListAdapter;
import app.com.example.carlos.tfgipol.R;

/**
 * Created by Carlos on 05/03/2016.
 */
public class RowParametersBuilder {
    Context applicationContext;

    public RowParametersBuilder(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void createRow(JSONObject json, View view) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.input_row_layout);
        TextView rowTextView = (TextView) view.findViewById(R.id.input_row_text);
        try {
            if (rowTextView.getText().equals(setText(json)))return;
            rowTextView.setText(setText(json));
            setInputComponent(json, layout);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String setText(JSONObject input) throws JSONException {
        return input.get("label").toString();
    }

    private void setInputComponent(JSONObject json, LinearLayout layout)throws JSONException {
        if (json.has("type_format")){
            switch (json.get("type_format").toString()){
                case "selection_collapsed":  createSpinner(json,layout);
                    break;
                case "text_slider":  createSlider(json,layout);
                    break;
            }
        }else {
            createEditText(json,layout);
        }
    }

    private void createSlider(JSONObject input, LinearLayout layout) {
        final TextView sliderValue = new TextView(applicationContext);
        sliderValue.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        sliderValue.setTextColor(Color.BLACK);
        sliderValue.setText("0");
        layout.addView(sliderValue);
        SeekBar seekBar = new SeekBar(applicationContext);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,3));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sliderValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBar.setBackgroundColor(Color.LTGRAY);
        layout.addView(seekBar);
    }

    private void createEditText(JSONObject input, LinearLayout layout) {
        EditText text = new EditText(applicationContext);
        text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        text.setInputType(InputType.TYPE_CLASS_NUMBER);
        text.setMaxWidth(80);
        text.setBackgroundColor(Color.LTGRAY);
        layout.addView(text);
    }

    private void createSpinner(JSONObject input, LinearLayout layout) {
        String[] arraySpinner = new String[] {"2", "5", "10", "15", "20", "25", "30", "35", "40"};
        Spinner spinner = new Spinner(applicationContext);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 3));
        spinner.setAdapter(new ArrayAdapter<>(applicationContext, android.R.layout.simple_spinner_item, arraySpinner));
        spinner.setBackgroundColor(Color.LTGRAY);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
                AppController.getInstance().setParam(parent.getItemAtPosition(pos).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        layout.addView(spinner);
    }


}
