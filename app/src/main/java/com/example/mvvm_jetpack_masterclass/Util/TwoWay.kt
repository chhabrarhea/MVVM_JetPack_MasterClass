package com.example.mvvm_jetpack_masterclass.Util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwoWay : ViewModel() {
    lateinit var name:MutableLiveData<String>
    init {
        name.value="Rhea"
    }
}