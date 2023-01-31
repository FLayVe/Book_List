package com.example.book_list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.book_list.databinding.ItemBookListBinding
import com.example.book_list.model.Book

class BookListAdapter(): RecyclerView.Adapter<BookListViewHolder>() {

    private var bookList = emptyList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookListViewHolder(
        ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount() = bookList.size

    fun setData(books: List<Book>){
        this.bookList = books
        notifyDataSetChanged()
    }

}