package com.gaurav.neargroupassignment.roomdb


import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.gaurav.neargroupassignment.roomdb.dao.BookDao
import com.gaurav.neargroupassignment.roomdb.entity.Book

@Database(entities = [Book::class], version = 2)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao


    companion object {
        private var instance: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase? {
            if (instance == null) {
                synchronized(BookDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java, "books_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }

    }
    class PopulateDbAsyncTask(db: BookDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val bookDao = db?.bookDao()

        override fun doInBackground(vararg p0: Unit?) {
            bookDao?.insert(Book("name 1", "subject 1", "100"))
            bookDao?.insert(Book("name 2", "subject 2", "200"))
            bookDao?.insert(Book("name 3", "subject 3", "300"))
        }
    }

}