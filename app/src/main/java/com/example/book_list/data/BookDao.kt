package com.example.book_list.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.book_list.model.Book

@Dao
interface BookDao {

    @Insert
    suspend fun add(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM book_table")
    fun readAll(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE title LIKE :searchQuery OR author LIKE :searchQuery")
    fun search(searchQuery: String):LiveData<List<Book>>

    @Query("SELECT COUNT(title) FROM book_table")
    fun getCount(): LiveData<Int>

}