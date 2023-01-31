package com.example.book_list.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.book_list.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    val readAllBooks: LiveData<List<Book>>
    private var repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        readAllBooks = repository.readAllBooks
    }

    fun searchBook(searchQuery: String): LiveData<List<Book>> =repository.searchBook(searchQuery)

    fun addBook(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun deleteBook(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }
    }
}