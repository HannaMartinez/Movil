package com.bs.recyapp.activity.ui.Inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bs.recyapp.R;
import com.bs.recyapp.activity.ui.Favoritos.PasosManualidad;

public class InicioFragment extends Fragment {
    CardView cardCarton,cardBotella,cardLata, cardOtros;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        //mostrar
        cardCarton = view.findViewById(R.id.cardCarton);
        cardBotella = view.findViewById(R.id.cardPlastico);
        cardLata = view.findViewById(R.id.cardLatas);
        cardOtros = view.findViewById(R.id.cardOtros);

        cardOtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(view.getContext(), ManualidadesActivity.class);
                I.putExtra("categoria","otros");
                startActivity(I);
            }
        });

        cardLata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent latasI = new Intent(view.getContext(), ManualidadesActivity.class);
                latasI.putExtra("categoria","latas");
                startActivity(latasI);
            }
        });
        cardBotella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botellaI = new Intent(view.getContext(), ManualidadesActivity.class);
                botellaI.putExtra("categoria","botellas");
                startActivity(botellaI);
            }
        });
        cardCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartonI = new Intent(view.getContext(), ManualidadesActivity.class);
                cartonI.putExtra("categoria","carton");
                startActivity(cartonI);
            }
        });
        return view;
    }

}