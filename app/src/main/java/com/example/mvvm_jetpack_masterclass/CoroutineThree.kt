package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_jetpack_masterclass.Util.ViewModel

class CoroutineThree : AppCompatActivity() {
    private lateinit var model:ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_three)
        model=ViewModelProvider(this).get(ViewModel::class.java)
        model.getData()
        model.students.observe(this, Observer {
            it.forEach {
                Log.i("jc",it.name)
            }
        })
    }
}