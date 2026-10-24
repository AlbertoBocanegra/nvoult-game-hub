package com.example.nvoulltgamehub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        db.collection("games").document("juego_01")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val titulo = document.getString("title")
                    Log.d("FIREBASE_TEST", "¡Conexión exitosa! Juego encontrado: $titulo")
                } else {
                    Log.d("FIREBASE_TEST", "El documento 'juego_01' no existe en la colección")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FIREBASE_TEST", "Error al conectar con Firestore: ", exception)
            }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}