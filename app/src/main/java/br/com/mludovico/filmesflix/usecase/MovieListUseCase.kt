package br.com.mludovico.filmesflix.usecase

import br.com.mludovico.filmesflix.data.MovieRepository

class MovieListUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getAllMoviesFromDataSource()
}