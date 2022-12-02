package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BurbujasActivity extends AppCompatActivity {
    BubbleChart bubbleChart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burbujas);

        bubbleChart1 = (BubbleChart) findViewById(R.id.bubblechart1);

        crearGraficaBurbujas();
    }

    private void crearGraficaBurbujas(){
        ArrayList<BubbleEntry> bubbleEntries = new ArrayList<>();
        bubbleEntries.add(new BubbleEntry(1,4,0.5f));
        bubbleEntries.add(new BubbleEntry(3,7,0.2f));
        bubbleEntries.add(new BubbleEntry(5,2,0.1f));
        bubbleEntries.add(new BubbleEntry(7,7,0.8f));

        BubbleDataSet bubbleDataSet = new BubbleDataSet(bubbleEntries, "Proyecto 2022");
        bubbleDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        BubbleData bubbleData = new BubbleData(bubbleDataSet);
        bubbleChart1.setData(bubbleData);
        bubbleChart1.getDescription().setText("Proyecto ISC");

        XAxis x = bubbleChart1.getXAxis();
        x.setAxisMaximum(10);
        x.setAxisMinimum(-1);
    }
}