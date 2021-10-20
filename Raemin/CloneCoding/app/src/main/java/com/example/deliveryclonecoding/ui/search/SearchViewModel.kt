package com.example.deliveryclonecoding.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryclonecoding.data.remote.base.wrappingAPICallResult
import com.example.deliveryclonecoding.data.remote.search.RestaurantRepository
import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantItem
import com.example.deliveryclonecoding.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : BaseViewModel() {

    val querySubject = BehaviorSubject.create<String>()

    private val searchSubject = PublishSubject.create<String>()

    private val _restaurants: MutableLiveData<List<RestaurantItem>> = MutableLiveData()
    val restaurants: LiveData<List<RestaurantItem>> = _restaurants

    init {
        querySubject
            .subscribe()
            .addTo(compositeDisposable)

        searchSubject
            .flatMapSingle {
                restaurantRepository
                    .search(it)
                    .wrappingAPICallResult()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.throwable != null) {
                    it.throwable.printStackTrace()
                } else {
                    it.result?.let { restaurantsSearchResult ->
                        _restaurants.value = restaurantsSearchResult.restaurants
                    }
                }
            }, { it.printStackTrace() })
            .addTo(compositeDisposable)
    }

    fun search() {
        querySubject.value?.let {
            searchSubject.onNext(it)
        }
    }
}