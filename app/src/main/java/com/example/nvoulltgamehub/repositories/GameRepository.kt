package com.example.nvoultgamehub.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.example.nvoultgamehub.models.Game

class GameRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection("games")

    // CREATE (Registrar un juego)
    fun addGame(game: Game, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        collectionRef.add(game)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }

    // READ (Consultar catálogo)
    fun getGames(onSuccess: (List<Game>) -> Unit, onFailure: (Exception) -> Unit) {
        collectionRef.get()
            .addOnSuccessListener { result ->
                val gameList = mutableListOf<Game>()
                for (document in result) {
                    val game = document.toObject(Game::class.java).copy(id = document.id)
                    gameList.add(game)
                }
                onSuccess(gameList)
            }
            .addOnFailureListener { e -> onFailure(e) }
    }

    // DELETE (Eliminar un juego)
    fun deleteGame(gameId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        collectionRef.document(gameId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }
}