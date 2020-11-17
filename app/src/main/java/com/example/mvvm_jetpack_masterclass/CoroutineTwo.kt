package com.example.mvvm_jetpack_masterclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_jetpack_masterclass.Util.StructuredCoroutine
import com.example.mvvm_jetpack_masterclass.Util.UnstructuredCoroutine
import kotlinx.android.synthetic.main.activity_coroutine_two.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Structured and Unstructured Concurrency
class CoroutineTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_two)
        CoroutineScope(Dispatchers.Main).launch {
            text1.text = UnstructuredCoroutine().getTotalUserCount().toString() //Kotlin Synthetic for directly referring to textview
            text.text = StructuredCoroutine().getTotalUserCount().toString()
        }
    }
}