package com.example.androidseminarstudy.tdd

import android.content.Context
import com.example.androidseminarstudy.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

object DateUtils {

    object InvalidUpdateTime : Throwable()

    fun convertToUpdateDate(context: Context, updated: String): String {
        val updatedDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        updatedDateFormat.timeZone = TimeZone.getTimeZone("GMT")

        val updatedDate = updatedDateFormat.parse(updated)
        val nowMillis = System.currentTimeMillis()
        val millisecondsUpdatedDate = updatedDate?.time ?: throw Exception()
        val difference = if (nowMillis > millisecondsUpdatedDate) {
            nowMillis - millisecondsUpdatedDate
        } else throw InvalidUpdateTime

        return when {
            difference < MinuteToMilliSeconds -> {
                context.getString(R.string.date_just_moment_ago)
            }
            difference < HourToMilliSeconds -> {
                String.format(context.getString(R.string.date_minutes_ago), difference / MinuteToMilliSeconds)
            }
            difference < DayToMilliSeconds -> {
                String.format(context.getString(R.string.date_hours_ago), difference / HourToMilliSeconds)
            }
            difference < DayToMilliSeconds * 2 -> {
                context.getString(R.string.date_one_day_ago)
            }
            /*difference < DayToMilliSeconds * 3 -> {
                context.getString(R.string.date_two_day_ago)
            }*/
            difference < DayToMilliSeconds * 7 -> {
                String.format(context.getString(R.string.date_n_day_ago), difference / DayToMilliSeconds)
            }
            /*difference < DayToMilliSeconds * 8 -> {
                context.getString(R.string.date_a_week_ago)
            }*/
            else -> {
                val simpleDateFormat = SimpleDateFormat("yyyy.M.dd", Locale.KOREA)
                simpleDateFormat.format(updatedDate)
            }
        }
    }


    fun convertToUpdateDate(context: Context, updated: String, instant: Instant): String {
        val updatedDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        updatedDateFormat.timeZone = TimeZone.getTimeZone("GMT")

        val updatedDate = updatedDateFormat.parse(updated)
        val nowMillis = instant.toEpochMilli()
        val millisecondsUpdatedDate = updatedDate?.time ?: throw Exception()
        val difference = if (nowMillis > millisecondsUpdatedDate) {
            nowMillis - millisecondsUpdatedDate
        } else throw InvalidUpdateTime

        return when {
            difference < MinuteToMilliSeconds -> {
                context.getString(R.string.date_just_moment_ago)
            }
            difference < HourToMilliSeconds -> {
                String.format(context.getString(R.string.date_minutes_ago), difference / MinuteToMilliSeconds)
            }
            difference < DayToMilliSeconds -> {
                String.format(context.getString(R.string.date_hours_ago), difference / HourToMilliSeconds)
            }
            difference < DayToMilliSeconds * 2 -> {
                context.getString(R.string.date_one_day_ago)
            }
            /*difference < DayToMilliSeconds * 3 -> {
                context.getString(R.string.date_two_day_ago)
            }*/
            difference < DayToMilliSeconds * 7 -> {
                String.format(context.getString(R.string.date_n_day_ago), difference / DayToMilliSeconds)
            }
            /*difference < DayToMilliSeconds * 8 -> {
                context.getString(R.string.date_a_week_ago)
            }*/
            else -> {
                val simpleDateFormat = SimpleDateFormat("yyyy.M.dd", Locale.KOREA)
                simpleDateFormat.format(updatedDate)
            }
        }
    }

    // Time constants
    private const val Milliseconds = 1000
    private const val MinuteToMilliSeconds = 60 * Milliseconds
    private const val HourToMilliSeconds = 60 * MinuteToMilliSeconds
    private const val DayToMilliSeconds = 24 * HourToMilliSeconds
}