package com.example.book_list.ui

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.book_list.databinding.ItemBookListBinding
import com.example.book_list.model.Book

class BookListViewHolder(private val binding: ItemBookListBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Book){
        binding.book = item

        binding.itemLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToBookInfoFragment(item)
            binding.root.findNavController().navigate(action)
        }
    }
}