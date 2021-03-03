package br.com.mludovico.filmesflix.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mludovico.filmesflix.framework.api.MovieRestApiTask
import br.com.mludovico.filmesflix.data.MovieRepository
import br.com.mludovico.filmesflix.domain.Movie
import br.com.mludovico.filmesflix.implementations.MovieDataSourceImplementation
import br.com.mludovico.filmesflix.usecase.MovieListUseCase

class MovieListViewModel: ViewModel() {

    companion object {
        const val TAG = "MovieRepository"
    }

    private val movieRestApiTask = MovieRestApiTask()
    private val movieDataSource = MovieDataSourceImplementation(movieRestApiTask)
    private val movieRepository = MovieRepository(movieDataSource)
    private val movieListUseCase = MovieListUseCase(movieRepository)

    private var _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
    get() = _movieList

    fun init() {
        getAllMovies()
    }

    private fun getAllMovies() {
        Thread {
            try {
                _movieList.postValue(movieListUseCase.invoke())
            } catch(exception: Exception) {
                Log.d(TAG, exception.message.toString())
            }
        }.start()
    }
}