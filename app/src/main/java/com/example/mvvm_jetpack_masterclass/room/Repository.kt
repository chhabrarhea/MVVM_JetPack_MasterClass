package com.example.mvvm_jetpack_masterclass.room

class Repository(private val dao:Dao) {

    val subs=dao.getAll()

    suspend fun insertSub(sub:Subscriber):Long
    {
        return dao.insertSubscriber(sub)
    }
    suspend fun insertSubs(subs:List<Subscriber>):List<Long>
    {
        return dao.insertSubscribers(subs)
    }
    suspend fun deleteSub(sub:Subscriber):Int
    {
        return  dao.deleteSubscriber(sub)
    }
    suspend fun updateSub(sub:Subscriber):Int
    {
       return dao.updateSubscriber(sub)
    }
    suspend fun deleteAll()
    {
        dao.deleteAll()
    }
}