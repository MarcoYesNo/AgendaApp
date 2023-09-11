package com.example.storeuaz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ma.quotidien.android.agendaapp.Room.data.Typeconverter


@Database(entities= [User::class,Programme::class,Partage::class],version=1,exportSchema=false)
@TypeConverters(Typeconverter::class)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao() : UserDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE=instace
                return instace
            }
        }
    }
}