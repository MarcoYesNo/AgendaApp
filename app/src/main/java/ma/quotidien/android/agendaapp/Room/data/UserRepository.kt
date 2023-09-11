package com.example.storeuaz.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val id:Int=0
    val readAllData: LiveData<List<User>> = userDao.readData()
    val readProgramme: LiveData<List<Programme>> = userDao.readDataProgramme()
    val readPartage:LiveData<List<Partage>> = userDao.readDataPartage()

    suspend fun addUser(user: User)
    {
        userDao.addUser(user)
    }
    suspend fun deleteUser(user: User)
    {
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUser()
    {
        userDao.deleteAllUser()
    }
    //Programme-----------------
    suspend fun addProg(programme: Programme)
    {
        userDao.insertProgramme(programme)
    }
    suspend fun deleteProg(programme: Programme)
    {
        userDao.deleteProg(programme)
    }
//PARATAGE
    suspend fun addPart(programme: Partage)
    {
        userDao.insertPartage(programme)
    }
    suspend fun deletePart(programme: Partage)
    {
        userDao.deletePartage(programme)
    }

     fun readPartageById(id:Int)
    {
        userDao.readDataPartageById(id)
    }
}