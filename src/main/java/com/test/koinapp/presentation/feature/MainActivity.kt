package com.test.koinapp.presentation.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.koinapp.R
import com.test.koinapp.presentation.common.CommonState
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "main_activity"

class MainActivity : AppCompatActivity() {

    private val movieListViewModel by viewModel<MovieListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMovies()
    }

    private fun getMovies() {
        movieListViewModel.getMovies(page = 1)
        movieListViewModel.commonLiveData.observe(this, { result ->
            when (result) {
                CommonState.ShowLoading -> {}
                CommonState.HideLoading -> {}
            }
        })
        movieListViewModel.moviesListLiveData.observe(this, { result ->
            Log.d(TAG, result.toString())
        })
    }
}