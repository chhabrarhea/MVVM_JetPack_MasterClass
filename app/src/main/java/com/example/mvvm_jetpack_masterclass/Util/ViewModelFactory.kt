package com.example.mvvm_jetpack_masterclass.Util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_jetpack_masterclass.ViewModelActivity
import java.lang.IllegalArgumentException

class ViewModelFactory(private val name:String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
           if(modelClass.isAssignableFrom(ViewModel::class.java))
               return com.example.mvvm_jetpack_masterclass.Util.ViewModel(name) as T
        throw IllegalArgumentException("Unknown View Model Class")
    }
}