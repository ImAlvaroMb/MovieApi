package com.example.movieapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.movieapi.databinding.ItemMovieBinding


class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        // Cargar la imagen con Picasso
        Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(holder.binding.moviePoster)
        /*Picasso.get().load("https://image.tmdb.org/t/p/w500/xI1qzf4q93PKqu9QpoW5hwW5QuG.jpg%22").into(holder.binding.moviePoster)*/
        println("PosterPath:" + movie.backdrop_path)

        // Establecer el título utilizando Data Binding
        holder.binding.movieTitle.text = movie.title

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("bgContent","https://image.tmdb.org/t/p/w500${movie.poster_path}\"")
            intent.putExtra("title", movie.title)
            intent.putExtra("overview", movie.overview)
            intent.putExtra("releasedate", movie.release_date)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}