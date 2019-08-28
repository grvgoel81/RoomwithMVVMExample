package com.gaurav.neargroupassignment.roomdb.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(

    var name: String,

    var subject: String,

    var price: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}