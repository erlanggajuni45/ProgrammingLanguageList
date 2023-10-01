package com.erlanggajuni.programminglanguagelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private fun getListLanguages(): ArrayList<ProgrammingLanguage> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPros = resources.getStringArray(R.array.data_pros)
        val dataCons = resources.getStringArray(R.array.data_cons)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listLanguage = ArrayList<ProgrammingLanguage>()

        for (i in dataName.indices) {
            val language = ProgrammingLanguage(dataName[i], dataDescription[i], dataPros[i], dataCons[i], dataPhoto.getResourceId(i, -1))
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
        Toast.makeText(this,language.name, Toast.LENGTH_SHORT).show()
    }
}