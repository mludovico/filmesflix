package br.com.mludovico.filmesflix.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mludovico.filmesflix.R
import br.com.mludovico.filmesflix.domain.Movie
import coil.load
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val moviesList: List<Movie>): RecyclerView.Adapter<MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.itemView.apply {
            image_text.text = moviesList[position].titulo
            image_view.load(moviesList[position].imagem) {
                placeholder(R.drawable.ic_placeholder)
                fallback(R.drawable.ic_placeholder)
            }
        }
    }

    override fun getItemCount(): Int = moviesList.size
}

class MovieItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
