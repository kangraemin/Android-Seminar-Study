package com.lcw.study.clonebaemin.data.search.remote

import com.lcw.study.clonebaemin.data.ApiService
import com.lcw.study.clonebaemin.data.search.RequestSearchData
import com.lcw.study.clonebaemin.data.search.SearchData
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService) : SearchDataSource {


    override fun getSearch(apiKey:String,query: String, page: Int): Single<SearchData> {
        return apiService.getSearch(apiKey,query, page)
            .subscribeOn(Schedulers.io())
    }
}