package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//Coroutines enable cooperative multitasking i.e processes control their own behaviour.
//Phones have a refresh rate of more than 60Hz therefore it is important that the main thread does not do any heavy computing.
//If the work of main thread does not complete till the time the phone screen refreshes,it can lead to various performance errors.
//A single thread can have multiple coroutines executing at the same time.
//We can control a coroutine from multiple threads irrespective of in which thread it was created.
//We can write synchronous code for a coroutine thus reducing boilerplate code.
class CoroutineOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_one)
    }
}