package com.test.koinapp.presentation.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.koinapp.domain.model.Movie
import com.test.koinapp.domain.use_cases.GetMoviesUseCase
import com.test.koinapp.presentation.common.CommonState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val movieModelMapper: MovieModelMapper
) : ViewModel() {

    private val _commonLiveData = MutableLiveData<CommonState>()
    val commonLiveData: LiveData<CommonState> = _commonLiveData

    private val _moviesListLiveData = MutableLiveData<List<MovieModel>>()
    val moviesListLiveData: LiveData<List<MovieModel>> = _moviesListLiveData

    fun getMovies(page: Int) {
        viewModelScope.launch(exceptionHandler()) {
            _commonLiveData.value = CommonState.ShowLoading
            val list = moviesUseCase.getMovieList(page).second
            val movieModels = list.map { movieModelMapper.from(it) }
            _moviesListLiveData.value = movieModels //getTestMovies()
            _commonLiveData.value = CommonState.HideLoading
        }
    }

    private fun getTestMovies(): List<MovieModel> {
        val movies = ArrayList<MovieModel>()
        for (i in 1..100) {
            movies.add(
                MovieModel(
                    id = i,
                    originalTitle = "Title: $i"
                )
            )
        }
        return movies
    }

    private fun exceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { context, throwable ->
            throwable.printStackTrace()
        }
}