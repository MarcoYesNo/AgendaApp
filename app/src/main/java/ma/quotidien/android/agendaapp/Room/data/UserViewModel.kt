package com.example.storeuaz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData:LiveData<List<User>>
    val readAllProgramme:LiveData<List<Programme>>
    val readAllPartage:LiveData<List<Partage>>

    val repository:UserRepository

    init {
        val userDao=UserDatabase.getDatabase(application).userDao()
        repository= UserRepository(userDao)
        readAllData= repository.readAllData
        readAllProgramme=repository.readProgramme
        readAllPartage=repository.readPartage


    }



    fun addUser(user: User)
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun deleteUser(user: User)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
    fun deleteAllUser()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

    //---------------------------------------------------//
    fun addProg(programme: Programme)
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.addProg(programme)
        }
    }
    fun deleteProg(programme: Programme)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProg(programme)
        }
    }

    //---------------------------------------------------//
    fun addPart(programme: Partage)
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.addPart(programme)
        }
    }
    fun deletePart(programme: Partage)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePart(programme)
        }
    }

    // Mettez à jour la déclaration de la fonction selectPartageById pour utiliser suspend et retourner un Partage
    fun selectPartageById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.readPartageById(id)
        }
    }

}