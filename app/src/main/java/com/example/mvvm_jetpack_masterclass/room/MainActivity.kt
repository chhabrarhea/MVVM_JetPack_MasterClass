package com.example.mvvm_jetpack_masterclass.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mvvm_jetpack_masterclass.R


class MainActivity : AppCompatActivity() {
    lateinit var binding: com.example.mvvm_jetpack_masterclass.databinding.ActivityMainBinding
    lateinit var viewModel:SubscriberViewModel
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = Database.getInstance(application).dao
        val repository = Repository(dao)
        val factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displaySubscriberList()
    {
           viewModel.subscribers.observe(this, Observer {
                adapter.setList(it)
               adapter.notifyDataSetChanged()
           })
    }
    private fun initRecyclerView()
    {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
        adapter= RecyclerViewAdapter { item:Subscriber->itemClicked(item) }
        binding.subscriberRecyclerView.adapter=adapter
        displaySubscriberList()
    }
    private fun itemClicked(sub:Subscriber)
    {
        viewModel.initUpdateAndDelete(sub)
    }

}