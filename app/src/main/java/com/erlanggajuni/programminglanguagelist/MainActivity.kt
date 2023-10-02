package com.erlanggajuni.programminglanguagelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.erlanggajuni.programminglanguagelist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = ArrayList<ProgrammingLanguage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvLanguages.setHasFixedSize(true)

        list.addAll(getListLanguages())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                // Tindakan yang akan dijalankan saat ikon di klik
                // Misalnya, tampilkan pesan toast
                Toast.makeText(this, "Ikon di klik!", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun getListLanguages(): ArrayList<ProgrammingLanguage> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesignBy = resources.getStringArray(R.array.data_design_by)
        val dataReleaseYear = resources.getStringArray(R.array.data_release_year)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPros = resources.getStringArray(R.array.data_pros)
        val dataCons = resources.getStringArray(R.array.data_cons)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listLanguage = ArrayList<ProgrammingLanguage>()

        for (i in dataName.indices) {
            val language = ProgrammingLanguage(dataName[i], dataDesignBy[i], dataReleaseYear[i], dataDescription[i], dataPros[i], dataCons[i], dataPhoto.getResourceId(i, -1))
            listLanguage.add(language)
        }
        return listLanguage
    }

    private fun showRecyclerList() {
        binding.rvLanguages.layoutManager = LinearLayoutManager(this)
        val listProgrammingLanguageAdapter = ListProgrammingLanguageAdapter(list)
        binding.rvLanguages.adapter = listProgrammingLanguageAdapter

        listProgrammingLanguageAdapter.setOnItemClickCallback(object: ListProgrammingLanguageAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ProgrammingLanguage) {
                showSelectedLanguage(data)
            }
        })
    }

    private fun showSelectedLanguage(language: ProgrammingLanguage) {
        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.DETAIL_LANGUAGE, language)
        startActivity(detailIntent)
    }
}