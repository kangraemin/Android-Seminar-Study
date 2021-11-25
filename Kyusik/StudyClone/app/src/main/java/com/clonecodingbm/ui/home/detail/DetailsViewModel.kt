package com.clonecodingbm.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.clonecodingbm.data.remote.base.Resource
import com.clonecodingbm.data.remote.home.Movie
import com.clonecodingbm.data.repository.home.HomeRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    repository: HomeRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _movie = MutableLiveData<Resource<Movie>>()
    val movie: LiveData<Resource<Movie>>
        get() = _movie

    init {
        if (savedStateHandle.contains("movieId")) {
            val movieId = savedStateHandle.get<Long>("movieId")
            compositeDisposable.add(
                repository.getMovieDetails(movieId!!)
                    .doOnSubscribe{_movie.value = Resource.Loading()}
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ movie ->
                        _movie.value = Resource.Success(movie)
                    }, { error ->
                        _movie.value = Resource.Error(error.message!!)
                    })
            )
        } else {
            _movie.value = Resource.Error("OMG, unable to get the argument!!")
            Timber.i("OMG, unable to get the argument!!")
        }
    }
}