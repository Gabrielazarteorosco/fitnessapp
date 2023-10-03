package com.gabriela.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gabriela.fitnessapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var screen: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screen = ActivityMainBinding.inflate(layoutInflater)
        setContentView(screen.root)
        logar()
    }

    private fun logar() {
        val email = screen.email.text.toString()
        val pass = screen.senha.text.toString()

        screen.login.setOnClickListener {
            if (email.isEmpty() || pass.isEmpty()) {
                val erro = Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT)
                erro.show()
            } else {
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val screenHome = Intent(this, Home::class.java)
                        startActivity(screenHome)
                        finish()
                    }
                }.addOnFailureListener{
                    val erroLogin = Toast.makeText(this, "Login ou senha errado", Toast.LENGTH_SHORT)
                    erroLogin.show()
                }
            }
        }
    }
}