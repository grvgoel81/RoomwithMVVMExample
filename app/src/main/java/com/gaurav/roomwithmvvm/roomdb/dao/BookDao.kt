package com.gaurav.neargroupassignment.roomdb.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.gaurav.neargroupassignment.roomdb.entity.Book

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @Query("DELETE FROM book_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM book_table ")
    fun getAllNotes(): LiveData<List<Book>>

}