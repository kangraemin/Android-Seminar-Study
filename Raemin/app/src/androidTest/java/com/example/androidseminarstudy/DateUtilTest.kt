package com.example.androidseminarstudy

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidseminarstudy.tdd.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*

@RunWith(AndroidJUnit4::class)
class DateUtilTest {

    private lateinit var instrumentContext: Context

    /*
    함수 목적
    - 유저가 댓글을 달거나, 게시글을 올렸을 때 게시글이 언제 업로드 된 것인지 계산해주기

    업무 규칙
    게시글이 업로드된 시간으로부터 1분 미만으로 지났다면, "방금전" 으로 표기
    게시글이 업로드된 시간으로부터 59분 미만으로 지났다면, "N분전" 으로 표기
    게시글이 업로드된 시간으로부터 24시간 미만으로 지났다면, "N시간전" 으로 표기
    게시글이 업로드된 시간으로부터 24시간 이상 ~ 48시간 미만으로 지났다면, "어제"로 표기
    게시글이 업로드된 시간으로부터 48시간 이상 ~ 7일 미만으로 지났다면 "N일전"으로 표기
    게시글이 업로드된 시간으로부터 7일 이상 지났다면, 업로드된 시간을 "YYYY.M.dd" 형식으로 표기

    */
    private enum class UpdatedDateTest(
        private val dateUnit: ChronoUnit,
        private val amount: Long,
        val expectedText: String
    ) {
        ERROR_FOR_PAST_TIME(ChronoUnit.SECONDS, -100, "asdf"),
        FIFTY_NINE_SECONDS_AGO(ChronoUnit.SECONDS, 59, "방금전"),
        ONE_SECOND_AGO(ChronoUnit.SECONDS, 1, "방금전"),
        SIXTY_SECONDS_AGO(ChronoUnit.SECONDS, 60, "1분전"),
        FIVE_MINUTES_AGO(ChronoUnit.MINUTES, 5, "5분전"),
        FIFTY_NINE_MINUTES_AGO(ChronoUnit.MINUTES, 59, "59분전"),
        SIXTY_MINUTES_AGO(ChronoUnit.MINUTES, 60, "1시간전"),
        ONE_MINUTE_BEFORE_DAY(ChronoUnit.MINUTES, 60 * 24 - 1, "23시간전"),
        ONE_DAY_AGO(ChronoUnit.DAYS, 1, "어제"),
        ONE_MINUTE_AFTER_DAY(ChronoUnit.MINUTES, 60 * 24 + 1, "어제"),
        TWO_DAY_AGO(ChronoUnit.DAYS, 2, "2일전"),
        THREE_DAYS_AGO(ChronoUnit.DAYS, 3, "3일전"),
        SIX_DAYS_AGO(ChronoUnit.DAYS, 6, "6일전"),
        SEVEN_DAY_AGO(ChronoUnit.DAYS, 7, "2021.1.23"),
        EIGHT_DAY_AGO(ChronoUnit.DAYS, 8, "2021.1.22");

        private val dateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }

        val updatedDateForTest: String by lazy {
            dateFormat.format(Date.from(instant.minus(amount, dateUnit)))
        }

        companion object {
            val instant: Instant = Instant.now(
                Clock.fixed(
                    Instant.parse("2021-01-30T00:00:00Z"), ZoneOffset.UTC
                )
            )
        }
    }

    private enum class UpdatedDateTestForNoInjection(
        private val dateUnit: Int,
        private val amount: Int,
        val expectedText: String
    ) {
        ERROR_FOR_PAST_TIME(Calendar.SECOND, 100, "asdf"),
        FIFTY_NINE_SECONDS_AGO(Calendar.SECOND, -59, "방금전"),
        ONE_SECOND_AGO(Calendar.SECOND, -1, "방금전"),
        SIXTY_SECONDS_AGO(Calendar.SECOND, -60, "1분전"),
        FIVE_MINUTES_AGO(Calendar.MINUTE, -5, "5분전"),
        FIFTY_NINE_MINUTES_AGO(Calendar.MINUTE, -59, "59분전"),
        SIXTY_MINUTES_AGO(Calendar.MINUTE, -60, "1시간전"),
        ONE_MINUTE_BEFORE_DAY(Calendar.MINUTE, -(60 * 24 - 1), "23시간전"),
        ONE_DAY_AGO(Calendar.DATE, -1, "어제"),
        ONE_MINUTE_AFTER_DAY(Calendar.MINUTE, -(60 * 24 + 1), "어제"),
        TWO_DAY_AGO(Calendar.DATE, -2, "2일전"),
        THREE_DAYS_AGO(Calendar.DATE, -3, "3일전"),
        SIX_DAYS_AGO(Calendar.DATE, -6, "6일전"),
        SEVEN_DAY_AGO(Calendar.DATE, -7, "2021.8.12"),
        EIGHT_DAY_AGO(Calendar.DATE, -8, "2021.8.11");

        private val dateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }

        val updatedDateForTest: String = dateFormat.format(Calendar.getInstance().run {
            add(dateUnit, amount)
            return@run time
        })
    }

    @Before
    fun setUp() {
        instrumentContext = ApplicationProvider.getApplicationContext()
    }

    @Test(expected = DateUtils.InvalidUpdateTime::class)
    fun testErrorForPastTime() {
        assertEquals(
            UpdatedDateTest.ERROR_FOR_PAST_TIME.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.ERROR_FOR_PAST_TIME.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForFiftyNineSecondsAgo() {
        assertEquals(
            UpdatedDateTest.FIFTY_NINE_SECONDS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.FIFTY_NINE_SECONDS_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForOneSecondAgo() {
        assertEquals(
            UpdatedDateTest.ONE_SECOND_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.ONE_SECOND_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForSixtySecondsAgo() {
        assertEquals(
            UpdatedDateTest.SIXTY_SECONDS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.SIXTY_SECONDS_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForFiveMinutesAgo() {
        assertEquals(
            UpdatedDateTest.FIVE_MINUTES_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.FIVE_MINUTES_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForFiftyNineMinutesAgo() {
        assertEquals(
            UpdatedDateTest.FIFTY_NINE_MINUTES_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.FIFTY_NINE_MINUTES_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForSixtyMinutesAgo() {
        assertEquals(
            UpdatedDateTest.SIXTY_MINUTES_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.SIXTY_MINUTES_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForOneMinuteBeforeDay() {
        assertEquals(
            UpdatedDateTest.ONE_MINUTE_BEFORE_DAY.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.ONE_MINUTE_BEFORE_DAY.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForOneDayAgo() {
        assertEquals(
            UpdatedDateTest.ONE_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.ONE_DAY_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForOneMinuteAfterDay() {
        assertEquals(
            UpdatedDateTest.ONE_MINUTE_AFTER_DAY.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.ONE_MINUTE_AFTER_DAY.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForTwoDayAgo() {
        assertEquals(
            UpdatedDateTest.TWO_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.TWO_DAY_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForThreeDaysAgo() {
        assertEquals(
            UpdatedDateTest.THREE_DAYS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.THREE_DAYS_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForSixDaysAgo() {
        assertEquals(
            UpdatedDateTest.SIX_DAYS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.SIX_DAYS_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForSevenDayAgo() {
        assertEquals(
            UpdatedDateTest.SEVEN_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.SEVEN_DAY_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test
    fun testForEightDayAgo() {
        assertEquals(
            UpdatedDateTest.EIGHT_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTest.EIGHT_DAY_AGO.updatedDateForTest,
                instant = UpdatedDateTest.instant
            )
        )
    }

    @Test(expected = DateUtils.InvalidUpdateTime::class)
    fun testForErrorForPastTimeNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.ERROR_FOR_PAST_TIME.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.ERROR_FOR_PAST_TIME.updatedDateForTest
            )
        )
    }

    @Test
    fun testForFiftyNineSecondsAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.FIFTY_NINE_SECONDS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.FIFTY_NINE_SECONDS_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForOneSecondAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.ONE_SECOND_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.ONE_SECOND_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForSixtySecondsAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.SIXTY_SECONDS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.SIXTY_SECONDS_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForFiveMinutesAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.FIVE_MINUTES_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.FIVE_MINUTES_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForFiftyNineMinutesAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.FIFTY_NINE_MINUTES_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.FIFTY_NINE_MINUTES_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForSixtyMinutesAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.SIXTY_MINUTES_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.SIXTY_MINUTES_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForOneMinuteBeforeDayNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.ONE_MINUTE_BEFORE_DAY.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.ONE_MINUTE_BEFORE_DAY.updatedDateForTest
            )
        )
    }

    @Test
    fun testForOneDayAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.ONE_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.ONE_DAY_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForOneMinuteAfterDayNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.ONE_MINUTE_AFTER_DAY.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.ONE_MINUTE_AFTER_DAY.updatedDateForTest
            )
        )
    }

    @Test
    fun testForTwoDayAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.TWO_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.TWO_DAY_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForThreeDaysAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.THREE_DAYS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.THREE_DAYS_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForSixDaysAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.SIX_DAYS_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.SIX_DAYS_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForSevenDayAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.SEVEN_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.SEVEN_DAY_AGO.updatedDateForTest
            )
        )
    }

    @Test
    fun testForEightDayAgoNoInstantInjection() {
        assertEquals(
            UpdatedDateTestForNoInjection.EIGHT_DAY_AGO.expectedText,
            DateUtils.convertToUpdateDate(
                context = instrumentContext,
                updated = UpdatedDateTestForNoInjection.EIGHT_DAY_AGO.updatedDateForTest
            )
        )
    }
}