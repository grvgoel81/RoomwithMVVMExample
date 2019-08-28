package com.gaurav.neargroupassignment.adapter

import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gaurav.neargroupassignment.roomdb.entity.Book
import com.gaurav.roomwithmvvm.R

class BookAdapter : RecyclerView.Adapter<BookAdapter.NoteHolder>() {
    private var books: List<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val book = books[position]
        holder.tvName.text = book.name
        holder.tvSubject.text = book.subject
        holder.tvPrice.text = book.price.toString()
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun setBooks(notes: List<Book>) {
        this.books = notes
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

    }
}