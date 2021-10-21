package com.lcw.study.clonebaemin.feature.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.lcw.study.clonebaemin.MyApplication
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.FragmentSearchBinding
import com.lcw.study.clonebaemin.feature.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by viewModels()
    var currentPosition = 0

    lateinit var chipView: Chip
    lateinit var chipItem: View

    //핸들러 설정
    //ui 변경하기
    val handler = Handler(Looper.getMainLooper()) {
        setPage()
        true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = searchViewModel

        chipItem = layoutInflater.inflate(R.layout.item_chip, null)
        chipView = chipItem.findViewById(R.id.chip_recent_search) as Chip

        val getlist = MyApplication.prefs.getStringArrayPref("query")
        Log.d("search", "getQuery : ${MyApplication.prefs.getList("query")}")

        getlist.let {
            for (value in getlist) {
                Log.d("getQuery", "Get json : $value")
                addSearchWord(value)
            }
        }




        binding.viewPagerBanner.adapter = ViewPagerAdapter(getBannerList())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.setQuery("",false)
                searchViewModel.getSearch(
                    "iGR3hRvQ.K3wPNmJgqSMeY6CehCZmuq7Kg5Hnw3o7",
                    query.toString(),
                    1
                )

                var list = ArrayList<String>()
                list.add(query.toString())

                MyApplication.prefs.setStringArrayPref("query", list)

                Log.d("search", "getQuery : ${MyApplication.prefs.getList("query")}")
                query?.let {
                    addSearchWord(query)
                }


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("검색", "검색어변경 : $newText")
                return false
            }

        })

        binding.btnDeleteAll.setOnClickListener {
            Log.d("search", "getQuery before : ${MyApplication.prefs.getList("query")}")
            MyApplication.prefs.removeKey("query")
            Log.d("search", "getQuery remove : ${MyApplication.prefs.getList("query")}")
        }

        chipView.setOnCloseIconClickListener {
            Log.d("chip", "text : ${chipView.text}")
            binding.chipgroup.removeView(chipView)
            getlist.remove(chipView.text)
            MyApplication.prefs.setStringArrayPref("query", getlist)
        }


        //뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()


    }

    private fun setPage() {
        if (currentPosition == 2) currentPosition = 0
        binding.viewPagerBanner.setCurrentItem(currentPosition, true)
        currentPosition += 1
    }


    fun addSearchWord(searchWord: String) {
        chipView.text = searchWord
        binding.chipgroup.addView(chipItem)


    }

    // 뷰 페이저에 들어갈 아이템
    private fun getBannerList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.ic_mybamin, R.drawable.ic_search)
    }


    //2초 마다 페이지 넘기기
    inner class PagerRunnable : Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}