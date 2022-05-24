package com.d3if2122.motivation_app.model

import com.d3if2122.motivation_app.db.QuotesEntity

fun QuotesEntity.generateQuotes(): Quotes {
    // TODO
    val nama = nama
    val jenisKelamin = jenisKelamin

    fun quotes(): String {
        return "Jangan patah semangat " + nama + ", kamu adalah " + jenisKelamin + " yang kuat!"
    }

    val quotesText = quotes()

    return Quotes(nama, jenisKelamin, quotesText)
}