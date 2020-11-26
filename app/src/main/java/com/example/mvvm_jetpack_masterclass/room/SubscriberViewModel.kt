package com.example.mvvm_jetpack_masterclass.room

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repo:Repository):ViewModel(),Observable {
    val subscribers = repo.subs
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber
    @Bindable
    val name=MutableLiveData<String>()
    @Bindable
    val email=MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }




    fun saveOrUpdate() {
        if (name.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
        } else if (email.value == null) {
            statusMessage.value = Event("Please enter subscriber's email")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value!!).matches()) {
            statusMessage.value = Event("Please enter a correct email address")
        } else {
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = name.value!!
                subscriberToUpdateOrDelete.email = email.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                val n = name.value!!
                val e = email.value!!
                insert(Subscriber(0, n, e))
                name.value = null
                email.value = null
            }
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        } else {
              clearAll()
        }

    }

    fun insert(subscriber: Subscriber) = viewModelScope.launch {
        val newRowId = repo.insertSub(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
         
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        val noOfRows = repo.updateSub(subscriber)
        if (noOfRows > 0) {
            name.value = null
            email.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        val noOfRowsDeleted = repo.deleteSub(subscriber)

        if (noOfRowsDeleted>0) {
            name.value = null
            email.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"

        } else {
           statusMessage.value=Event("Error Occured! :(")
        }

    }

    fun clearAll()=viewModelScope.launch {
        repo.deleteAll()

    }
    fun initUpdateAndDelete(subscriber: Subscriber) {
        name.value = subscriber.name
        email.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}