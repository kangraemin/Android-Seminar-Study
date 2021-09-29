package com.terry.delivery.ui.search

/*
 * Created by Taehyung Kim on 2021-09-29
 */
data class SearchRankItem(
    val rank: Int,
    val title: String,
    val rankChanged: Int
) {

    fun getRankChanged(): RankStatus {
        return when (rankChanged) {
            0 -> RankStatus.RANK_UP
            1 -> RankStatus.RANK_DOWN
            2 -> RankStatus.RANK_IDLE
            else -> RankStatus.RANK_IDLE
        }
    }

    enum class RankStatus {
        RANK_UP, RANK_DOWN, RANK_IDLE
    }
}
