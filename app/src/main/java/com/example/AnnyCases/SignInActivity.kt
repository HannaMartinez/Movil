package com.example.AnnyCases

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.AnnyCases.databinding.ActivitySignInBinding
import com.example.AnnyCases.ui.Usuario.UsuarioFragment
import com.example.AnnyCases.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
try {
    binding = ActivitySignInBinding.inflate(layoutInflater)
    setContentView(binding.root)
    auth = Firebase.auth

        binding.signInAppCompatButton.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()

            when {
                mPassword.isEmpty() || mEmail.isEmpty() -> {
                    Toast.makeText(this, "Email o contraseña o incorrectos.",
                        Toast.LENGTH_SHORT).show()
                }
                else -> {
                    signIn(mEmail, mPassword)
                }
            }

        }



        binding.signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            this.startActivity(intent)
        }

        binding.recoveryAccountTextView.setOnClickListener {
            val intent = Intent(this, AccountRecoveryActivity::class.java)
            this.startActivity(intent)
        }
}catch (e: Exception){
    Toast.makeText(this,"Error "+ e, Toast.LENGTH_LONG).show()
}
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                reload()
            } else {
                val intent = Intent(this, CheckEmailActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    reload()
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Email o contraseña o incorrectos.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload() {
        try {
            val intent = Intent(this@SignInActivity, UsuarioFragment::class.java)
            startActivity(intent)

        }catch (e: Exception){

        }
    }
}