package com.clonecodingbm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.remote.base.Resource
import com.clonecodingbm.data.remote.home.Movie
import com.clonecodingbm.data.repository.home.HomeRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: HomeRepository,
) : BaseViewModel() {
    //    private val _trendingMovies = MutableLiveData<Resource<List<Movie>>>()
//    val trendingMovies: LiveData<Resource<List<Movie>>>
//        get() = _trendingMovies
    private val _trendingMovies = MutableLiveData<Resource<List<Movie>>>()
    val trendingMovies: LiveData<Resource<List<Movie>>> = _trendingMovies

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    private val positionInterval = Observable
        .interval(1000, TimeUnit.MILLISECONDS)
        .takeWhile { it < 20 }
        .doOnNext { Thread.sleep(2000) }
        .repeat()

    init {
        compositeDisposable.add(
            repository
                .getTrendingMovie()
                .doOnSubscribe { _trendingMovies.value = Resource.Loading(null) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies ->
                    _trendingMovies.value = Resource.Success(movies.results)
                }, { error ->
                    Timber.e(error)
                    _trendingMovies.value = Resource.Error(error.message!!, null)
                })
        )
        compositeDisposable.add(
            positionInterval
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _position.value = it.toInt()
                }, {
                    it.printStackTrace()
                }, {
                    _position.value = 0
                })
        )
    }
}