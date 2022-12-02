package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineaActivity extends AppCompatActivity {
    LineChart lieneachart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea);

        lieneachart1 = (LineChart)  findViewById(R.id.lieneachart1);
        crearGraficaLinea();
    }

    private void crearGraficaLinea(){
        ArrayList<Entry> lineaEntries = new ArrayList<>();
        lineaEntries.add(new Entry(0,20));
        lineaEntries.add(new Entry(1,24));
        lineaEntries.add(new Entry(2,2));
        lineaEntries.add(new Entry(3,10));
        lineaEntries.add(new Entry(4,28));

        LineDataSet lineDataSet = new LineDataSet(lineaEntries, "Proyecto 2022");
        lineDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        LineData lineData = new LineData(lineDataSet);

        lieneachart1.setData(lineData);
        lieneachart1.getDescription().setText("Grafica de proyectos semestrales");
        XAxis x = lieneachart1.getXAxis();
        x.setAxisMinimum(0);
        x.setAxisMaximum(7);
    }
}