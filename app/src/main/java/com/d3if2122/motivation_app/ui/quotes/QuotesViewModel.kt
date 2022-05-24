package com.d3if2122.motivation_app.ui.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if2122.motivation_app.db.QuotesDao
import com.d3if2122.motivation_app.db.QuotesEntity
import com.d3if2122.motivation_app.model.JenisKelamin
import com.d3if2122.motivation_app.model.Quotes
import com.d3if2122.motivation_app.model.generateQuotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuotesViewModel(private val db: QuotesDao): ViewModel() {
    private val quotes = MutableLiveData<Quotes?>()

    fun generateQuotes(nama: String, jenisKelamin: JenisKelamin) {
        val dataQuotes = QuotesEntity(
            nama = nama,
            jenisKelamin = jenisKelamin,
        )
        quotes.value = dataQuotes.generateQuotes()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataQuotes)
            }
        }
    }

    fun getQuotes(): LiveData<Quotes?> = quotes
}