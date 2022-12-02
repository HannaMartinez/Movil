package com.hanna.graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {
    PieChart piechart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        piechart1 = (PieChart) findViewById(R.id.piechart1);
        crearGraficaPie();
    }

    private void crearGraficaPie(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(50,"Matematicas"));
        pieEntries.add(new PieEntry(60,"Español"));
        pieEntries.add(new PieEntry(100,"Historia"));
        pieEntries.add(new PieEntry(90,"Quimica"));
        pieEntries.add(new PieEntry(80,"Geografia"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"calificaciones semestrales");
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        piechart1.setData(pieData);
        piechart1.getDescription().setEnabled(false);
        piechart1.setCenterText("Calificacion");
        piechart1.animate();
    }
}