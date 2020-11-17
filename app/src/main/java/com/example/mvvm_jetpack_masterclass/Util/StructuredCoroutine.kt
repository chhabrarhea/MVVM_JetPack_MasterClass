package com.example.mvvm_jetpack_masterclass.Util

import kotlinx.coroutines.*
import kotlinx.coroutines.coroutineScope as coroutineScope

class StructuredCoroutine {
    var count = 0
    lateinit var deferred : Deferred<Int>
    suspend fun getTotalUserCount():Int {

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