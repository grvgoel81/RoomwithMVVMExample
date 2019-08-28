package com.gaurav.neargroupassignment

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.gaurav.neargroupassignment.roomdb.BookDatabase
import com.gaurav.neargroupassignment.roomdb.dao.BookDao
import com.gaurav.neargroupassignment.roomdb.entity.Book

class BookRepository(application: Application) {

    private var bookDao: BookDao

    private var allNotes: LiveData<List<Book>>

    init {
        val database: BookDatabase = BookDatabase.getInstance(
            application.applicationContext
        )!!
        bookDao = database.bookDao()
        allNotes = bookDao.getAllNotes()
    }

    fun insert(book: Book) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(bookDao).execute(book)
    }

    fun deleteAllBooks() {
        val deleteAllNotesAsyncTask = DeleteAllBooksAsyncTask(
            bookDao
        ).execute()
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(bookDao: BookDao) : AsyncTask<Book, Unit, Unit>() {
        val bookDao = bookDao

        override fun doInBackground(vararg p0: Book?) {
            bookDao.insert(p0[0]!!)
        }
    }


    private class DeleteAllBooksAsyncTask(val bookDao: BookDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            bookDao.deleteAllNotes()
        }
    }

}