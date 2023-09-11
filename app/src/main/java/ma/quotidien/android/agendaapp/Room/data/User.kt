package com.example.storeuaz.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName= "user_table")
data class User(
    @PrimaryKey(autoGenerate=true)
    val id:Int,
    val noms: String?,
    val pseudo: String?,
    val mdp: String?
)

@Entity(tableName= "programme_table")
data class Programme(
    @PrimaryKey(autoGenerate=true)
    val id:Int,
    val idUser:Int,
    val content: String?,
    val date:Date?
    )

@Entity(tableName= "partage_table")
data class Partage(
    @PrimaryKey(autoGenerate=true)
    val id:Int,
    val idRecepteur: Int?,
    val idEnvoyeur:Int?,
    val content: String?,
    val date: Date?
)
