package com.erlanggajuni.programminglanguagelist

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.erlanggajuni.programminglanguagelist.databinding.ActivityDetailBinding
import com.erlanggajuni.programminglanguagelist.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val DETAIL_LANGUAGE = "detail_language"
    }
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        binding.actionShare.setOnClickListener{
        Toast.makeText(this,"Berhasil dibagikan!", Toast.LENGTH_SHORT).show()
        }

        val language = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<ProgrammingLanguage>(DETAIL_LANGUAGE, ProgrammingLanguage::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<ProgrammingLanguage>(DETAIL_LANGUAGE)
        }

        if (language != null) {
            binding.detailPhoto.setImageResource(language.photo)
            binding.languageNameDetail.text = language.name
            binding.designBy.text = "Didesain oleh: ${language.designBy}"
            binding.releaseYear.text = "Tahun rilis: ${language.releaseYear}"
            binding.descriptionDetail.text = language.description

            val prosItem = language.pros.split(", ")
            val consItem = language.cons.split(", ")

            var prosString: String = ""
            var consString: String = ""

            for (index in prosItem.indices) {
                var item = prosItem[index]
                prosString = if (prosString == "") "${index+1}. $item\n"
                else "$prosString${index+1}. $item\n"
            }

            for (index in consItem.indices) {
                var item = prosItem[index]
                consString = if (consString == "") "${index+1}. $item\n"
                else "$consString${index+1}. $item\n"
            }

            binding.listPros.text = prosString
            binding.listCons.text = consString
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}