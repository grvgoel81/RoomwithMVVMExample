package com.gaurav.roomwithmvvm.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.anubhav87.mvvmtutorial.viewmodel.BookViewModel
import com.gaurav.neargroupassignment.adapter.BookAdapter
import com.gaurav.neargroupassignment.roomdb.entity.Book
import com.gaurav.roomwithmvvm.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val ADD_BOOK_REQUEST = 1
    private lateinit var noteViewModel: BookViewModel
    private val adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        buttonAddBook.setOnClickListener {
            startActivityForResult(
                Intent(this, AddBookActivity::class.java),
                ADD_BOOK_REQUEST
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        noteViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        noteViewModel.getAllBooks().observe(this,
            Observer<List<Book>> { t -> adapter.setBooks(t!!) })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item?.itemId) {
            R.id.delete_all_books -> {
                noteViewModel.deleteAllBooks()
                Toast.makeText(this, "All books deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_BOOK_REQUEST && resultCode == Activity.RESULT_OK) {
            val newBook = Book(
                data!!.getStringExtra(AddBookActivity.EXTRA_NAME),
                data.getStringExtra(AddBookActivity.EXTRA_SUBJECT),
                data.getStringExtra(AddBookActivity.EXTRA_PRICE)
            )
            noteViewModel.insert(newBook)

            Toast.makeText(this, "Book saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Book not saved!", Toast.LENGTH_SHORT).show()
        }


    }
}

