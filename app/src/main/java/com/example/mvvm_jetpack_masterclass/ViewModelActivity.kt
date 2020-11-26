package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_jetpack_masterclass.coroutines.ViewModel
import com.example.mvvm_jetpack_masterclass.util.ViewModelFactory
import com.example.mvvm_jetpack_masterclass.databinding.ActivityViewModelBinding

//The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
// it allows data to survive configuration changes such as screen rotations, keyboard changes, language changes.
//UI controllers like activity and fragments, should only be responsible for controlling the UI and responding to user inputs.
//Assigning excessive responsibility to UI controllers like data handling can result in a single class that tries to handle all of an app's work by itself, instead of delegating work to other classes.
//It's easier and more efficient to separate out view data ownership from UI controller logic.
//ViewModel objects are scoped to the Lifecycle passed to the ViewModelProvider when getting the ViewModel. The ViewModel remains in memory until the Lifecycle it's scoped to goes away permanently: in the case of an activity, when it finishes, while in the case of a fragment, when it's detached.
//We set an observer on the live data which has its own lifecycle and automatically provides the updates value to the activity.

class ViewModelActivity : AppCompatActivity() {
    lateinit var binding:ActivityViewModelBinding
    lateinit var vm: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=DataBindingUtil.setContentView(this,R.layout.activity_view_model)
        var vmf=ViewModelFactory("rhea")
        vm=ViewModelProvider(this,vmf).get(ViewModel::class.java)
        vm.nameValue.observe(this, Observer {
            binding.textview.setText(it)
        })
        binding.button.setOnClickListener {
            vm.setName(binding.edittext1.text.toString())
        }
    }
}