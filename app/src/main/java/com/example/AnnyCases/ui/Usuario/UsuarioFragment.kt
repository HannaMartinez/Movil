package com.example.AnnyCases.ui.Usuario

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.bumptech.glide.Glide
import com.example.AnnyCases.DeleteAccountActivity
import com.example.AnnyCases.R
import com.example.AnnyCases.SignInActivity
import com.example.AnnyCases.UpdatePasswordActivity
import com.example.AnnyCases.databinding.ActivityMainBinding
import com.example.AnnyCases.databinding.FragmentUsuarioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

@Suppress("DEPRECATION")
class UsuarioFragment : AppCompatActivity()   {

    private var _binding: FragmentUsuarioBinding? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: FragmentUsuarioBinding
    private lateinit var auth: FirebaseAuth
    private val fileResult = 1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val bindings get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = FragmentUsuarioBinding.inflate(layoutInflater)
            setContentView(binding.root)
            auth = Firebase.auth

            updateUI()
            binding.updateProfileAppCompatButton.setOnClickListener {
                val name = binding.nameEditText.text.toString()

                updateProfile(name)
            }

            binding.profileImageView.setOnClickListener {
                fileManager()
            }

            binding.deleteAccountTextView.setOnClickListener {
                val intent = Intent(this@UsuarioFragment, DeleteAccountActivity::class.java)
                this.startActivity(intent)
            }

            binding.updatePasswordTextView.setOnClickListener {
                val intent = Intent(this, UpdatePasswordActivity::class.java)
                this.startActivity(intent)
            }

            binding.signOutImageView.setOnClickListener {
                signOut()
            }
        }catch (e: Exception){
            Toast.makeText(this,"Error "+ e, Toast.LENGTH_LONG).show()
        }
    }
    private  fun updateProfile (name : String) {

        val user = auth.currentUser


        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Se realizaron los cambios correctamente.",
                        Toast.LENGTH_SHORT).show()
                    updateUI()
                }
            }
    }

    private fun fileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, fileResult)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == fileResult) {
            if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
                val uri = data.data

                uri?.let { imageUpload(it) }

            }
        }
    }

    private fun imageUpload(mUri: Uri) {

        val user = auth.currentUser
        val folder: StorageReference = FirebaseStorage.getInstance().reference.child("Users")
        val fileName: StorageReference = folder.child("img"+user!!.uid)

        fileName.putFile(mUri).addOnSuccessListener {
            fileName.downloadUrl.addOnSuccessListener { uri ->

                val profileUpdates = userProfileChangeRequest {
                    photoUri = Uri.parse(uri.toString())
                }

                user.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Se realizaron los cambios correctamente.",
                                Toast.LENGTH_SHORT).show()
                            updateUI()
                        }
                    }
            }
        }.addOnFailureListener {
            Log.i("TAG", "file upload error")
        }
    }



    private  fun updateUI () {
        val user = auth.currentUser

        if (user != null){
            binding.emailTextView.text = user.email

            if(user.displayName != null){
                binding.nameTextView.text = user.displayName
                binding.nameEditText.setText(user.displayName)
            }

            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_profile)
                .into(binding.profileImageView)
            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_profile)
                .into(binding.bgProfileImageView)
        }

    }

    private  fun signOut(){
        auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
        this.startActivity(intent)
    }

    private fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUsuarioBinding.inflate(inflater, container, false)
        val root: View = bindings.root


        return root
    }

    private fun onDestroyView() {
        super.onDestroy()
        _binding = null
    }
}