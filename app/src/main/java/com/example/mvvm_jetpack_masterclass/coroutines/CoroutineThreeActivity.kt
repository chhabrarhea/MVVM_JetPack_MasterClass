package com.example.mvvm_jetpack_masterclass.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_jetpack_masterclass.R
import kotlinx.coroutines.launch

class CoroutineThreeActivity : AppCompatActivity() {
    private lateinit var model: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_three)

        //viewModel Scope
        model=ViewModelProvider(this).get(ViewModel::class.java)
        model.getData()
        model.students.observe(this, Observer {
            it.forEach {
                Log.i("jc",it.name)
            }
        })

        //live data builder
        model.students1.observe(this,{
            it.forEach {
                Log.i("jc",it.name)
            }
        })

        //for activities and fragments
        lifecycleScope.launchWhenCreated {  }
        lifecycleScope.launchWhenResumed {  }
        lifecycleScope.launchWhenResumed {  }
        lifecycleScope.launch {  }
    }
}