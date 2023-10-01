package com.erlanggajuni.programminglanguagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erlanggajuni.programminglanguagelist.databinding.ItemRowProgrammingLanguageBinding

class ListProgrammingLanguageAdapter(private val listLanguage: ArrayList<ProgrammingLanguage>): RecyclerView.Adapter<ListProgrammingLanguageAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowProgrammingLanguageBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProgrammingLanguageBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listLanguage.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, _, _, description, _, _, photo) = listLanguage[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listLanguage[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProgrammingLanguage)
    }
}