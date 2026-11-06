package com.example.nvoultgamehub.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    // INICIO DE SESIÓN
    fun login(email: String, pass: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e.localizedMessage ?: "Error al iniciar sesión") }
    }

    // REGISTRO CON ROL POR DEFECTO (USER)
    fun register(email: String, pass: String, username: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { result ->
                val userId = result.user?.uid ?: ""
                val userMap = hashMapOf(
                    "username" to username,
                    "email" to email,
                    "role" to "USER" // Rol predeterminado para cumplir la rúbrica
                )
                db.collection("users").document(userId).set(userMap)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onFailure(e.localizedMessage ?: "Error al guardar perfil") }
            }
            .addOnFailureListener { e -> onFailure(e.localizedMessage ?: "Error al registrar") }
    }

    // CERRAR SESIÓN
    fun logout() {
        auth.signOut()
    }
}