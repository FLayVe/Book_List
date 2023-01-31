package com.example.book_list.data

import androidx.lifecycle.LiveData
import com.example.book_list.model.Book

class BookRepository(private val bookDao: BookDao) {

    val readAllBooks: LiveData<List<Book>> = bookDao.readAll()

    fun searchBook(searchQuery: String): LiveData<List<Book>> = bookDao.search(searchQuery)

    suspend fun addBook(book: Book){
        bookDao.add(book)
    }

    suspend fun deleteBook(book: Book){
        bookDao.delete(book)
    }

}