package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarrasActivity extends AppCompatActivity {
    BarChart barchart1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barras);

        barchart1 = (BarChart) findViewById(R.id.barchart1);

        crearGraficaBarras();
    }

    private void crearGraficaBarras(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,4));
        barEntries.add(new BarEntry(2,2));
        barEntries.add(new BarEntry(3,1));
        barEntries.add(new BarEntry(4,3));
        barEntries.add(new BarEntry(5,7));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Proyecto 2022");
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);//


        BarData barData = new BarData(barDataSet);

        barchart1.setData(barData);
        barchart1.getDescription().setText("Grafica de proyectos semestrales");
        XAxis x = barchart1.getXAxis();
        x.setAxisMinimum(0);
        x.setAxisMaximum(7);
    }
}