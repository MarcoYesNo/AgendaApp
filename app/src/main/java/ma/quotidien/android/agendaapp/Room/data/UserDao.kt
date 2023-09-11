package com.example.storeuaz.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface UserDao {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun readData():LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    //Programme----------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProgramme(prog: Programme)

    @Query("SELECT * FROM programme_table")
    fun readDataProgramme():LiveData<List<Programme>>

   /* @Query("SELECT * FROM programme_table WHERE idUser=:id")
    fun readDataProgrammeById(id:Int):LiveData<List<Programme>>*/

    @Delete
    suspend fun deleteProg(prog: Programme)

    //Partage----------------------------------
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPartage(prog: Partage)

    @Query("SELECT * FROM partage_table")
    fun readDataPartage():LiveData<List<Partage>>

    @Query("SELECT * FROM partage_table WHERE idRecepteur=:id")
    fun readDataPartageById(id:Int):LiveData<List<Partage>>

    @Delete
    suspend fun deletePartage(prog: Partage)


}