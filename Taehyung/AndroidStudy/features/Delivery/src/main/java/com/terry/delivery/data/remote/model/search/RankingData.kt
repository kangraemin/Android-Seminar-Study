package com.terry.delivery.data.remote.model.search

data class RankingData(
    val rank: Int,
    val rankChanged: Int,
    val title: String
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