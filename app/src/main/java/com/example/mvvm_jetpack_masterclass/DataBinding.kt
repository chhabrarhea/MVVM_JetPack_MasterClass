package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.mvvm_jetpack_masterclass.databinding.ActivityDataBindingBinding


// Using FindViewById android has to go through the view hierarchy to find the views at runtime.
//Android phones refresh at a rate of 60 times per second.
//This means android has to recreate a screen every 16ms.
//DataBinding occurs at compile time.
//DataBinding object creates a reference to all our views, this improves the performance and eliminates run time errors due to views.
//Directly makes object properties directly accessible to a layout
//We can also use Kotlin Synthetic (KTX) in smaller objects.

class DataBinding : AppCompatActivity() {
    private lateinit var binding:ActivityDataBindingBinding
    private lateinit var button:Button
    private lateinit var text:TextView
    private lateinit var edit:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        binding.s=getStudent()
        text=binding.textview
        edit=binding.edittext
        button=binding.button
        button.setOnClickListener {
            text.setText(edit.text)
            if (edit.text.toString().isEmpty())
                Toast.makeText(this,"Edittext is empty!",Toast.LENGTH_SHORT).show()
        }



    }
    private fun getStudent():  com.example.mvvm_jetpack_masterclass.util.StudentModel
    {
        return com.example.mvvm_jetpack_masterclass.util.StudentModel("Rhea",29,"rheachhabra1011@gmail.com")
    }
}