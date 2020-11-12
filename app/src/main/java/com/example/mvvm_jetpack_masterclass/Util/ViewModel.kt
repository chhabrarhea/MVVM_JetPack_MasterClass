package com.example.mvvm_jetpack_masterclass.Util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//LiveData object is only readable and not editable
//MutableLiveData is both readable and editable
//We can bind UI element with LiveData and vice versa-Two way binding
//if we need to pass some input data to the constructor of the viewModel , we need to create a factory class for viewModel.
//A ViewModel's onCleared() is called when the app is put into the background and the app process is killed in order to free up the system's memory.
//To keep the mutable live data private, we keep a live data variable in its place because private variable wont be visible to the activity to observe.
open class ViewModel(initialName:String): ViewModel() {
    private  var name=MutableLiveData<String>()
    val nameValue: LiveData<String>
    get() = name



    init {
        name.value=initialName
    }
    fun setName(n:String)
    {
        name.value=n
    }
    fun getName():String
    {
        return nameValue.value.toString()
    }
}