package br.com.mludovico.filmesflix.data

import br.com.mludovico.filmesflix.domain.Movie

interface MovieDataSource {
    fun getAllMovies(): List<Movie>
}