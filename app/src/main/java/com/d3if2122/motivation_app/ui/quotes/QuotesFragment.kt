package com.d3if2122.motivation_app.ui.quotes

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.d3if2122.motivation_app.R
import com.d3if2122.motivation_app.databinding.FragmentQuotesBinding
import com.d3if2122.motivation_app.db.QuotesDb
import com.d3if2122.motivation_app.model.JenisKelamin
import com.d3if2122.motivation_app.model.Quotes

class QuotesFragment: Fragment() {
    private lateinit var binding: FragmentQuotesBinding

    private val viewModel: QuotesViewModel by lazy {
        val db = QuotesDb.getInstance(requireContext())
        val factory = QuotesViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[QuotesViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentQuotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.generateQuotesButton.setOnClickListener { generateQuotes() }
        binding.shareButton.setOnClickListener{ shareData() }
        viewModel.getQuotes().observe(requireActivity(), { showResult(it) })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun generateQuotes() {
        val nama = binding.namaInp.text.toString()
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedPria = selectedId == R.id.priaRadioButton

        viewModel.generateQuotes(
            nama,
            jenisKelamin = if (selectedPria) {
                JenisKelamin.PRIA
            } else {
                JenisKelamin.WANITA
            }
        )
    }

    private fun showResult(result: Quotes?) {
        if (result == null) return
        binding.quotesText.text = result.quotesText
        binding.shareButton.visibility = View.VISIBLE
    }

    private fun getJenisKelaminLabel(jenisKelamin: JenisKelamin): String {
        val stringRes = when (jenisKelamin) {
            JenisKelamin.PRIA -> R.string.pria
            JenisKelamin.WANITA -> R.string.wanita
        }
        return getString(stringRes)
    }

    private fun shareData() {
        val message = "Hai, aku baru saja menggunakan motivation-app dan itu sangat memotivasi sekali!"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}