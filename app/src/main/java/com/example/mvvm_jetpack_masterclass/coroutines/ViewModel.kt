package com.example.mvvm_jetpack_masterclass.coroutines

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.mvvm_jetpack_masterclass.util.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//LiveData object is only readable and not editable
//MutableLiveData is both readable and editable
//We can bind UI element with LiveData and vice versa-Two way binding
//if we need to pass some input data to the constructor of the viewModel , we need to create a factory class for viewModel.
//A ViewModel's onCleared() is called when the app is put into the background and the app process is killed in order to free up the system's memory.
//To keep the mutable live data private, we keep a live data variable in its place because private variable wont be visible to the activity to observe.
open class ViewModel(initialName:String): ViewModel() {
    private var repo= StudentRepository()
    var students:MutableLiveData<List<StudentModel>> = MutableLiveData()

    //liveData Builder
    val students1= liveData(Dispatchers.IO) {
        val result=repo.getStudents();
        emit(result)
    }

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

    fun getData(){
        viewModelScope.launch {
          var result:List<StudentModel>?=null
            withContext(IO){
                result=repo.getStudents()
            }
            students.value=result
        }

    }


}