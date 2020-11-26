package com.example.mvvm_jetpack_masterclass.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.mvvm_jetpack_masterclass.room.Subscriber

@Dao
interface Dao {

    @Insert
    suspend fun insertSubscriber(sub:Subscriber):Long

    //returns id of rows created for verification
    @Insert
    suspend fun insertSubscribers(subs:List<Subscriber>):List<Long>

    //returns no. of rows affected
    @Update
    suspend fun updateSubscriber(sub:Subscriber):Int

    //returns no. of rows deleted
    @Delete
    suspend fun deleteSubscriber(sub:Subscriber):Int

    //Custom operations
    //will show compile time errors for syntactical errors in SQL statements
    @Query("Delete from subscriber_list")
    suspend fun deleteAll()

    //automatically runs on a background thread
    @Query("select * from subscriber_list")
    fun getAll():LiveData<List<Subscriber>>

}