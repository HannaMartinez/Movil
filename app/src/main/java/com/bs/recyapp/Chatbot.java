package com.bs.recyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chatbot extends AppCompatActivity {
    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;

    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<ChatsModal> chatsModalArrayList;
    private ChatRVAdapter chatRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chatsRV = findViewById(R.id.idRVChats);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        chatsModalArrayList = new ArrayList<>();
        chatRVAdapter = new ChatRVAdapter(chatsModalArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVAdapter);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    toastIncorrecto("");
                    //Toast.makeText(Chatbot.this, "Por favor escribe tu mensaje!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] strGroserias=new String[] {"pendejo", "menso", "estupido","idiota","imbecil","tarado","huevo",
                "gay","pendeja","estupida","fuck","mierda","pito","mensa","maricon","maricona","puñetas","bastador",
                "mamon","pinche","gonorrea","verga","culero","hijo de perra","chinga tu madre","me lleva la puta verga",
                "lamehuevos","chingadamadre"};
                for (int i = 0; i < strGroserias.length; i++) {
                    if (userMsgEdt.getText().toString().equals(strGroserias[i])) {
                        toastModerador("");
                        //Toast.makeText(Chatbot.this, "Por favor modera tu lenguaje!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getResponse(String message) {
        chatsModalArrayList.add(new ChatsModal(message, USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        //String url= "http://api.brainshop.ai/get?bid=167254&key=yzYir3liSrteM9p9&uid=[uid]&msg="+message;
        String url = "http://api.brainshop.ai/get?bid=166876&key=X2oF0gfP1pYsXb58&uid=[uid]&msg=" + message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if (response.isSuccessful()) {
                    MsgModal modal = response.body();
                    chatsModalArrayList.add(new ChatsModal(modal.getCnt(), BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsModalArrayList.add(new ChatsModal("Por favor devuelva su pregunta!", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
            }
        });
    }

    public void toastIncorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error,
                (ViewGroup) findViewById(R.id.ll_custom_toast_error));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public void toastModerador(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_moderador,
                (ViewGroup) findViewById(R.id.ll_custom_toast_moderador));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
