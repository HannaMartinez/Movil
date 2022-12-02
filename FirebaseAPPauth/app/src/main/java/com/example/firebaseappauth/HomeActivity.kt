package com.example.firebaseappauth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var tvEmail:TextView
    private lateinit var btnLogout:Button
    private lateinit var btnQuienes:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //enlaces
        tvEmail = findViewById<TextView>(R.id.tvEmail)
        btnLogout = findViewById<Button>(R.id.btnLogout)
        btnQuienes = findViewById<Button>(R.id.btnQuienes)
        //cargar email de usuario

        var bundle:Bundle? = intent.extras
        tvEmail.setText(bundle?.getString("email"))

        // oyente button
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

            btnQuienes.setOnClickListener(View.OnClickListener {
                val intent1 = Intent(applicationContext, QuienesActivity::class.java)
                startActivity(intent1)
            })
        }
    }


}