package com.lcw.study.clonebaemin.data.search.remote

import com.lcw.study.clonebaemin.data.login.LoginData
import com.lcw.study.clonebaemin.data.search.SearchData
import io.reactivex.Single

interface SearchDataSource {
    fun getSearch(apiKey:String,query: String, page: Int): Single<SearchData>
}