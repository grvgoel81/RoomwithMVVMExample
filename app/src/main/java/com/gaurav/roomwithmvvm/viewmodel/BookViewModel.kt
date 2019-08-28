package com.anubhav87.mvvmtutorial.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.gaurav.neargroupassignment.BookRepository
import com.gaurav.neargroupassignment.roomdb.entity.Book


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: BookRepository =
        BookRepository(application)

    private var allBooks: LiveData<List<Book>> = repository.getAllBooks()

    fun insert(book: Book) {
        repository.insert(book)
    }

    fun deleteAllBooks() {
        repository.deleteAllBooks()
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return allBooks
    }
}