package com.example.mvvm_jetpack_masterclass.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.mvvm_jetpack_masterclass.R

import kotlinx.coroutines.*

//Coroutines enable cooperative multitasking i.e processes control their own behaviour.
//Phones have a refresh rate of more than 60Hz therefore it is important that the main thread does not do any heavy computing.
//If the work of main thread does not complete till the time the phone screen refreshes,it can lead to various performance errors.
//A single thread can have multiple coroutines executing at the same time.
//We can control a coroutine from multiple threads irrespective of in which thread it was created.
//We can write synchronous code for a coroutine thus reducing boilerplate code.
class CoroutineOneActivity : AppCompatActivity() {
    lateinit var binding:com.example.mvvm_jetpack_masterclass.databinding.ActivityCoroutineOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_coroutine_one)
        binding.start.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               download()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.i("Message1","Hello from ${Thread.currentThread().name}")
        }
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("Message1","Hello from ${Thread.currentThread().name}")
        }
    }

    //switching to main thread to change UI
    private suspend fun download()
    {
        withContext(Dispatchers.Main)
        {
            for(i in 1..20000)
                {
                    binding.textview.setText("i= ${i} from ${Thread.currentThread().name}")
                    delay(1000)
                }
        }
    }
}