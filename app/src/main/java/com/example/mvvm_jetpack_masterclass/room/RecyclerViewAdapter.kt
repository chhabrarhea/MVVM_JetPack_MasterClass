package com.example.mvvm_jetpack_masterclass.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_jetpack_masterclass.R
import com.example.mvvm_jetpack_masterclass.databinding.RoomItemBinding


class RecyclerViewAdapter(private val clickListener: (Subscriber) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.VH>() {
    private val list = ArrayList<Subscriber>()

    class VH(val binding: com.example.mvvm_jetpack_masterclass.databinding.RoomItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: Subscriber, clickListener: (Subscriber) -> Unit) {
            binding.nameTextView.text = s.name
            binding.emailTextView.text = s.email
            binding.listItemLayout.setOnClickListener {
                clickListener(s)
            }
        }
    }

    fun setList(subscribers: List<Subscriber>) {
        list.clear()
        list.addAll(subscribers)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RoomItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.room_item, null, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}