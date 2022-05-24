package com.d3if2122.motivation_app.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if2122.motivation_app.db.QuotesDao

class QuotesViewModelFactory(
    private val db: QuotesDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return QuotesViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}