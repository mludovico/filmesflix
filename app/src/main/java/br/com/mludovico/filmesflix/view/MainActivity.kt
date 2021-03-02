package br.com.mludovico.filmesflix.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mludovico.filmesflix.R
import br.com.mludovico.filmesflix.model.Movie
import br.com.mludovico.filmesflix.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListViewModel = ViewModelProvider.NewInstanceFactory().create(MovieListViewModel::class.java)
        movieListViewModel.init()

        initObsever()
        loadingVisibility(true)
    }

    private fun initObsever() {
        movieListViewModel.movieList.observe(this, { movieList ->
            if (list.isNotEmpty()) {
                populateList(movieList)
                loadingVisibility(false)
            }
        })
    }

    private fun populateList(movieList: List<Movie>) {
        list.apply {
            hasFixedSize()
            adapter = MoviesAdapter(movieList)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun loadingVisibility(isLoading: Boolean) {
        progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}