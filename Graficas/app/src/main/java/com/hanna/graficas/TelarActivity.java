package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TelarActivity extends AppCompatActivity {
    RadarChart radarchart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telar);

        radarchart1 = (RadarChart) findViewById(R.id.radarchart1);
        GraficaTelar();
    }

    private void GraficaTelar(){
        ArrayList<RadarEntry> radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(420));
        radarEntries.add(new RadarEntry(419));
        radarEntries.add(new RadarEntry(418));
        radarEntries.add(new RadarEntry(417));
        radarEntries.add(new RadarEntry(416));
        radarEntries.add(new RadarEntry(419));

        RadarDataSet radarDataSet = new RadarDataSet(radarEntries,"Proyecto");
        radarDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        radarDataSet.setLineWidth(2f);

        radarDataSet.setValueTextSize(14f);

        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSet);
        String[] labels = {"2015", "2016", "2017", "2018", "2019", "2020", "2021"};

        XAxis xAxis = radarchart1.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarchart1.setData(radarData);
    }
}