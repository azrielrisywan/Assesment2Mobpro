package com.d3if2122.motivation_app.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.d3if2122.motivation_app.model.JenisKelamin
import com.d3if2122.motivation_app.model.Quotes
import kotlin.reflect.KFunction1

@Entity(tableName = "quotes")
data class QuotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nama: String,
    var jenisKelamin: JenisKelamin,
)