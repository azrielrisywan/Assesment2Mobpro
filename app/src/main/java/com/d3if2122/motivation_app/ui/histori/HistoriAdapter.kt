package com.d3if2122.motivation_app.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if2122.motivation_app.R
import com.d3if2122.motivation_app.databinding.ItemHistoriBinding
import com.d3if2122.motivation_app.db.QuotesEntity
import com.d3if2122.motivation_app.model.generateQuotes
import java.text.SimpleDateFormat
import java.util.*



class HistoriAdapter : ListAdapter<QuotesEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<QuotesEntity>() {
            override fun areItemsTheSame(oldData: QuotesEntity, newData: QuotesEntity): Boolean {
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(oldData: QuotesEntity, newData: QuotesEntity): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: QuotesEntity) = with(binding) {
            val quotes = item.generateQuotes()
            jenisKelaminTextView.text = quotes.jenisKelamin.toString().substring(0, 1)

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            namaTextView.text = quotes.nama
            historiQuotesTextView.text = quotes.quotesText
        }
    }
}