package com.example.mvvm_jetpack_masterclass.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class],version=1)
abstract class Database: RoomDatabase() {

    abstract val dao:Dao

    companion object{
        @Volatile
        private var INSTANCE:com.example.mvvm_jetpack_masterclass.room.Database?=null
        fun getInstance(c:Context):com.example.mvvm_jetpack_masterclass.room.Database
        {
            synchronized(this)
            {
                var i= INSTANCE
                if(i==null)
                {
                    i=Room.databaseBuilder(c.applicationContext,com.example.mvvm_jetpack_masterclass.room.Database::class.java,"subscriber_db")
                        .build()
                }
                return i
            }

        }
    }

}