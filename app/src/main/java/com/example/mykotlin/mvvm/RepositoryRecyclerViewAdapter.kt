package com.example.mykotlin.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.databinding.RvItemRepositoryBinding
import com.example.mykotlin.learn.Repository

class RepositoryRecyclerViewAdapter(private var items: ArrayList<Repository>, private var listener:OnItemClickListener)
    :RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],listener)
    }

    override fun getItemCount(): Int =items.size
    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }
    class ViewHolder(private var binding:RvItemRepositoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(repo:Repository,listener: OnItemClickListener?){
            binding.repository=repo
            if (listener!=null){
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })

            }
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent?.context)
        val binding=RvItemRepositoryBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)    }

    fun replaceData(list: java.util.ArrayList<Repository>) {
        items=list
        notifyDataSetChanged()
    }
}
