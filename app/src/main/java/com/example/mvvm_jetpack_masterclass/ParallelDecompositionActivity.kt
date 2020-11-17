package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mvvm_jetpack_masterclass.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ParallelDecompositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallel_decomposition)

        CoroutineScope(Main).launch {
            val a=async(IO){
                getValue1()

            }
            val b=async(IO) { getValue2() }
            val total=a.await()+b.await()
            Toast.makeText(applicationContext,"Total value is ${total}",Toast.LENGTH_SHORT)
        }
    }

    private suspend fun getValue1():Int{
        delay(10000)
        Log.i("getValue1","First value returned")
        return 35000
    }
    private suspend fun getValue2():Int{
        delay(8000)
        Log.i("getValue1","Second value returned")
        return 3000
    }
}