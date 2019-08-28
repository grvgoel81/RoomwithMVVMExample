package com.gaurav.roomwithmvvm.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.gaurav.roomwithmvvm.R
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "com.gaurav.neargroupassignment.EXTRA_NAME"
        const val EXTRA_SUBJECT = "com.gaurav.neargroupassignment.EXTRA_SUBJECT"
        const val EXTRA_PRICE = "com.gaurav.neargroupassignmentEXTRA.PRICE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_book_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_book -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if (etName.text.toString().trim().isBlank() || etSubject.text.toString().trim().isBlank() ||
             etPrice.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAME, etName.text.toString())
            putExtra(EXTRA_SUBJECT, etSubject.text.toString())
            putExtra(EXTRA_PRICE, etPrice.text.toString())
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
