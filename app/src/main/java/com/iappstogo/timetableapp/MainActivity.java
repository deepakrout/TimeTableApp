package com.iappstogo.timetableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTableListView;
    private static  final int MAX_TIMES_TABLE=20;
    private static  final int STARTING_POSITION=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTableSeekBar = (SeekBar) findViewById(R.id.timeTableSeekBar);
        timesTableListView = findViewById(R.id.timeTableListView);

        timesTableSeekBar.setMax(MAX_TIMES_TABLE);
        timesTableSeekBar.setProgress(STARTING_POSITION);

        generateTimesTable(getTimeTableContent(STARTING_POSITION));

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //get minimum times table number
                int timesTableNumber = getMinTableNum(seekBar,i);

                Log.i("Seekbar Value",Integer.toString(timesTableNumber));

                //get array list
                ArrayList<String> timesTableContent = new ArrayList<String>();
                timesTableContent = getTimeTableContent(timesTableNumber);

                //generate times table
                generateTimesTable(timesTableContent);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public int getMinTableNum(SeekBar seekBar,int idx){
        int min = 1;
        int timesTableNumber;

        if (idx < min ) {
            timesTableNumber = min;
            seekBar.setProgress(min);
        } else {
            timesTableNumber = idx;
        }
        return timesTableNumber;
    }

    public ArrayList<String> getTimeTableContent(int tableNumber){
        ArrayList<String> ttContent = new ArrayList<String>();
        for (int i=1; i<= 10; i++) {
            ttContent.add(Integer.toString(i * tableNumber));
        }
        return ttContent;
    }

    public void generateTimesTable(ArrayList<String> timesTableContent){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,timesTableContent);
        timesTableListView.setAdapter(arrayAdapter);
    }


}
