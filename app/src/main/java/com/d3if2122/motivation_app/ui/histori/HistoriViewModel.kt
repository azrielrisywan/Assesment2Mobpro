package com.d3if2122.motivation_app.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if2122.motivation_app.db.QuotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoriViewModel(private val db: QuotesDao) : ViewModel() {
    val data = db.getLastQuotes()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}