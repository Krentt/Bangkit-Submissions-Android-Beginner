package com.example.actionfigureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Figures>("DATA")
        Log.d("Detail data", data?.name.toString())

        supportActionBar?.title = "${data?.name} Figure"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvDetailName : TextView = findViewById(R.id.figure_name)
        val tvDetailDescription : TextView= findViewById(R.id.description_figure)
        val ivDetailPhoto : ImageView = findViewById(R.id.imageView)
        val tvDetailPrice : TextView = findViewById(R.id.price)
        val tvDetailSellerName : TextView = findViewById(R.id.seller_name)
        val tvDetailSellerPhoto : ImageView = findViewById(R.id.seller_image)

        tvDetailSellerName.text = data?.sellerName
        Glide.with(this).load(data?.sellerPhoto).into(tvDetailSellerPhoto)
        tvDetailPrice.text = data?.price
        tvDetailName.text = data?.name
        tvDetailDescription.text = data?.description
        if (data != null) {
            ivDetailPhoto.setImageResource(data.photo)
        }

        val btnShareActivity: Button = findViewById(R.id.action_share)
        btnShareActivity.setOnClickListener{
            val sendIntent : Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Cek Action Figure '${data?.name}' Demon Slayer seharga '${data?.price}' SAJA!!!!! TUNGGU APALAGI Unduh sekarang jika belum!")
                type= "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}