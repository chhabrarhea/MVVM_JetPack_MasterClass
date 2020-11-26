package com.example.mvvm_jetpack_masterclass.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.coroutineScope as coroutineScope

class StructuredCoroutine {
    var count = 0
    lateinit var deferred : Deferred<Int>
    suspend fun getTotalUserCount():Int {

        //suspending function
        //scope of parent coroutine ends only after that of all child coroutines
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }

            deferred = async(Dispatchers.IO){
                delay(3000)
                return@async 70
            }

        }
        return count + deferred.await()
    }
}