package com.example.actionfigureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFigures: RecyclerView
    private val list = ArrayList<Figures>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFigures = findViewById(R.id.rv_figures)
        rvFigures.setHasFixedSize(true)

        list.addAll(getListFigures())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profil -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFigures(): ArrayList<Figures>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataSellerName = resources.getStringArray(R.array.data_seller_name)
        val dataSellerPhoto = resources.getStringArray(R.array.data_seller_photo)
        val listFigures = ArrayList<Figures>()
        for (i in dataName.indices){
            val figure = Figures(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataPrice[i], dataSellerName[i], dataSellerPhoto[i])
            listFigures.add(figure)
        }
        return listFigures
    }

    private fun showRecyclerList(){
        rvFigures.layoutManager = LinearLayoutManager(this)
        val listFiguresAdapter = ListFigureAdapter(list)
        rvFigures.adapter = listFiguresAdapter

        listFiguresAdapter.setOnItemClickCallback(object : ListFigureAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Figures) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
}