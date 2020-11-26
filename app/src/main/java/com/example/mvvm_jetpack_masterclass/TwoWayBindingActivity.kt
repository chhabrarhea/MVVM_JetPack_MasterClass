package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_jetpack_masterclass.util.TwoWay
import com.example.mvvm_jetpack_masterclass.databinding.ActivityTwoWayBindingBinding

//we can use one way data binding to show the user some data (app to user data flow).
//We can also use one way data binding to get user input(user to app data flow) .
//If, for some reason, we want to both show data and get user input over the same widget, in other words, if we need a two-way data flow, we should use two-way data binding .
class TwoWayBindingActivity : AppCompatActivity() {
    lateinit var binding:ActivityTwoWayBindingBinding
    lateinit var vM:TwoWay
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_two_way_binding)
        vM=ViewModelProvider(this).get(TwoWay::class.java)
        binding.lifecycleOwner=this
        binding.sname=vM
    }
}