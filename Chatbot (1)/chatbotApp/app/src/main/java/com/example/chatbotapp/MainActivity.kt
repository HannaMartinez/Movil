package com.example.chatbotapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val  adapterChatBot = AdapterChatBot()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit=Retrofit.Builder()
            .baseUrl("http:192.168.8.101:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val apiService = retrofit.create(APIService::class.java)



        rvChatList.layoutManager = LinearLayoutManager(this)
        rvChatList.adapter = adapterChatBot

        btnSend.setOnClickListener{
            if(etChat.text.isNullOrEmpty()){
                Toast.makeText(this@MainActivity, "Por favor escriba algo", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            adapterChatBot.addChatToList(ChatModel(etChat.text.toString()))
            apiService.chatWithTheBit(etChat.text.toString()).enqueue(callBack)
                    etChat.text.clear()

        }

    }
       private val callBack = object : Callback<ChatResponse>{
           override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
               if (response.isSuccessful &&  response.body()!=null) {

                   adapterChatBot.addChatToList(ChatModel(response.body()!!.chatBotReply, true))

               }else{
                   Toast.makeText(this@MainActivity, "hubo algun error", Toast.LENGTH_LONG).show()
               }
           }

           override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
               Toast.makeText(this@MainActivity, "hubo algun error", Toast.LENGTH_LONG).show()

           }

       }
}