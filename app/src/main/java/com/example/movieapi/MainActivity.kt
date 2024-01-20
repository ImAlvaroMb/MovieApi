package com.example.movieapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        GlobalScope.launch(Dispatchers.IO) {
            val response = fetchMovies()
            launch(Dispatchers.Main) {

                if (response != null) {
                    // Verificar que la respuesta no sea nula
                    parseMovies(response)
                    println(response)
                } else {
                    println("No se recibió una respuesta válida.")
                }
            }
        }
    }

    private fun fetchMovies() : String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/trending/movie/day?language=en-US")
            .get()
            .addHeader("accept", "application/json")
            .addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYjc1YWViYjVlMzdiYmM2ZGQ2MjA0NjQyOGY0MTA5YSIsInN1YiI6IjY1NzFlODlkOTBmY2EzMDEyZDEyZmYzYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P9hNUL1zKJhXiQp_hd1UNxECJfNYuYgXz-H610tWRGM"
            )
            .build()

        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        return responseBody

    }

    private fun parseMovies(response: String) {
        try {
            val gson = Gson()
            val movieResponse = gson.fromJson(response, MovieResponse::class.java)

            if(movieResponse.results.isNotEmpty()) {
                movies.addAll(movieResponse.results)
                println("Movie" + movies[0].toString())
                initRecyclerView()
            } else {
                println("No files found on response")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error al parsear la respuesta JSON.")
        }
    }

        private fun initRecyclerView() {
            adapter = MovieAdapter(this, movies)
            val layoutManager = GridLayoutManager(this, 2)
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = adapter
        }
    }


