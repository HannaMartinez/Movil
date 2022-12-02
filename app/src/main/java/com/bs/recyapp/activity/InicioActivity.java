package com.bs.recyapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bs.recyapp.Chatbot;
import com.bs.recyapp.R;
import com.bs.recyapp.SplashChatbotActivity;
import com.bs.recyapp.UserActivity;
import com.bs.recyapp.activity.ui.Empresas.EmpresasFragment;
import com.bs.recyapp.databinding.ActivityInicioBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.sql.Time;

import de.hdodenhof.circleimageview.CircleImageView;

public class InicioActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityInicioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarInicio.toolbar);
        binding.appBarInicio.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(InicioActivity.this, SplashChatbotActivity.class));
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_favoritos,R.id.nav_inicio, R.id.nav_Empresa, R.id.nav_addman)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if (id == R.id.action_settings){
            finishAffinity();
            return true;
        }else
        if (id == R.id.itemCerrarS) {
            logout();
            return true;
        }else{
        if (id == R.id.action_user){
            Intent intent = new Intent(InicioActivity.this, UserActivity.class);
            startActivity(intent);
            return true;
        }
        }
        return super.onOptionsItemSelected(item);
    }
    /*@Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    private void loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String usuarioJson = sp.getString("UsuarioJson", null);
        if(usuarioJson != null){
            final Usuario u = g.fromJson(usuarioJson, Usuario.class);
            final View vistaHeader = binding.navView.getHeaderView(0);
            final TextView tvNombre = vistaHeader.findViewById(R.id.tvNombre),
                    tvCorreo = vistaHeader.findViewById(R.id.tvEmail);
            final CircleImageView imgFoto = vistaHeader.findViewById(R.id.imageUser);
            tvNombre.setText(u.getCliente().getNombreCompleto());
            tvCorreo.setText(u.getEmail());
            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + u.getCliente().getFoto().getFileName();
            final Picasso picasso = new Picasso.Builder(this)
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.user)
                    .into(imgFoto);
        }
    }*/

    private void logout() {
        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent2);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}