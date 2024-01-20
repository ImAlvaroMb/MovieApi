package com.example.movieapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapi.databinding.ActivityMoviedetailBinding
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMoviedetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviedetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val posterUrl = intent.getStringExtra("bgContent")
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val releaseDate = intent.getStringExtra("releasedate")
        val button = binding.button

        Picasso.get().load(posterUrl).into(binding.imageViewPoster)
        binding.textViewTitle.text = title
        binding.textViewOverview.text = overview
        binding.textReleaseDate.text = "Release Date: " + releaseDate


        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}