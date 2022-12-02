package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarrasHActivity extends AppCompatActivity {
    HorizontalBarChart horizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barras_hactivity);

        horizontal = (HorizontalBarChart) findViewById(R.id.horizontal);

        ArrayList<BarEntry> listEntri = new ArrayList<>();

        listEntri.add(new BarEntry(5,3));
        listEntri.add(new BarEntry(4,5));
        listEntri.add(new BarEntry(3,7));
        listEntri.add(new BarEntry(2,6));
        listEntri.add(new BarEntry(1,8));

        BarDataSet barDataSet = new BarDataSet(listEntri,"Grafica Horizontal");
        BarData barData = new BarData(barDataSet);
        horizontal.setData(barData);
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        horizontal.getDescription().setEnabled(false);
        horizontal.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        horizontal.getAxisLeft().setEnabled(false);
    }
}