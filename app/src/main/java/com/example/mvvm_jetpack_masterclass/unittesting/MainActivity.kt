package com.example.mvvm_jetpack_masterclass.unittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_jetpack_masterclass.R
import com.example.mvvm_jetpack_masterclass.databinding.ActivityMain2Binding
import com.example.mvvm_jetpack_masterclass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var viewModel: CalcViewModel
    lateinit var factory: CalcViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        factory = CalcViewModelFactory(MyCalc())
        viewModel = ViewModelProvider(this, factory)
            .get(CalcViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
    }
}