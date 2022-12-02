package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class OnduladaActivity extends AppCompatActivity {
    BarChart groupbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ondulada);

        groupbar = (BarChart) findViewById(R.id.groupbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CrearGraficaOndas(2*180);
    }

    private void CrearGraficaOndas(int count){
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i=0; i<count; i++){
            float radians = (float) Math.toRadians(i);
            float valor = (float) Math.sin(radians);
            entries.add(new BarEntry(i,valor));
        }
        BarDataSet barDataSet;
        barDataSet = new BarDataSet(entries,"Ondas");
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        BarData data = new BarData(barDataSet);
        data.setDrawValues(false);
        data.setBarWidth(0.9f);
        groupbar.setData(data);
    }
}