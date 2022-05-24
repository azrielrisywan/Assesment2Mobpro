package com.d3if2122.motivation_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuotesEntity::class], version = 1, exportSchema = false)
abstract class QuotesDb : RoomDatabase() {
    abstract val dao: QuotesDao
    companion object {
        @Volatile
        private var INSTANCE: QuotesDb? = null
        fun getInstance(context: Context): QuotesDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuotesDb::class.java,
                        "quotes.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}