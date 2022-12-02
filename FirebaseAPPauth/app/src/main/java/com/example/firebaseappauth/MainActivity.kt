package com.example.firebaseappauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    private lateinit var btnRegistrar:Button
    private lateinit var btnIngresar:Button
    private lateinit var btnGoogle:Button
    val GOOGLE_SING_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //enlaces
        etEmail = findViewById<EditText>(R.id.etEmail)
        etPassword = findViewById<EditText>(R.id.etPassword)
        btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnGoogle = findViewById<Button>(R.id.btnGoogle)

        //oyentes
        btnRegistrar.setOnClickListener { Registrar() }
        btnIngresar.setOnClickListener { Ingresar() }
        btnGoogle.setOnClickListener {
            val GoogleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("867967924244-manqlgjv75eo2chut95k7vj9bbeq278b.apps.googleusercontent.com")
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(applicationContext, GoogleConf)
            client.signOut()
            startActivityForResult(client.signInIntent, GOOGLE_SING_IN)
        }
    }

    private fun Registrar(){
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val intenten1: Intent = Intent(applicationContext, HomeActivity::class.java).apply {
                            putExtra( "email", it.result?.user?.email?: " ")
                        }
                        startActivity(intenten1)
                    }else
                        verDialogo("Error","Este correo ya existe")
                }
        }else
            verDialogo("Advertencia", "Debes llenar todos los campos")
    }

    private fun Ingresar(){
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val intenten1: Intent = Intent(applicationContext, HomeActivity::class.java).apply {
                            putExtra( "email", it.result?.user?.email?: " ")
                        }
                        startActivity(intenten1)
                    }else
                        verDialogo("Error","Error de autentificacion de usuario")
                }
        }else
            verDialogo("Advertencia", "Debes llenar todos los campos")

    }
    private fun verDialogo(titulo:String,mensaje:String){
        var builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == GOOGLE_SING_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val acount = task.getResult(ApiException::class.java)
                try {
                    if (acount == null) {
                    } else {
                        val credencial = GoogleAuthProvider.getCredential(acount.idToken, null)
                        FirebaseAuth.getInstance().signInWithCredential(credencial)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intenten1: Intent = Intent(applicationContext, HomeActivity::class.java).apply {
                                        putExtra( "email", it.result?.user?.email?: " ")
                                    }
                                    startActivity(intenten1)
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "No se pudo iniciar sesion",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }.addOnFailureListener{
                                Log.d("Error: ", it.message.toString())
                            }
                    }
                } catch (e: ApiException) {
                    Log.d("Error en credencial: ", e.message.toString())
                }
            }
        } catch (e: ApiException) {
            Log.d("Error df: ", e.message.toString())
        }
    }
}
