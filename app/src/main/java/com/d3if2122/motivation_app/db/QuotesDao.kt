package com.d3if2122.motivation_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuotesDao {
    @Insert
    fun insert(quotes: QuotesEntity)

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun getLastQuotes(): LiveData<List<QuotesEntity?>>

    @Query("DELETE FROM quotes")
    fun clearData()
}