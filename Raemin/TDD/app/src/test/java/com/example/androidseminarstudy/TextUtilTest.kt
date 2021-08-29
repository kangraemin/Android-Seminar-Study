package com.example.androidseminarstudy

import com.example.androidseminarstudy.tdd.TextUtils
import junit.framework.Assert.assertEquals
import org.junit.Test


class TextUtilTest {
    /*
    함수 목적
    - 유저가 회원가입 시, 비밀번호를 입력 했을 때, 비밀번호의 보안 점수를 계산하는 함수

    업무 규칙
    1. 비밀번호의 길이가 한글자 이상이면 점수 + 1
    2. 영어 대 / 소문자가 섞여서 들어가 있으면 점수 + 1
    3. 숫자가 들어가 있으면 점수 + 1
    4. 특수문자가 들어가 있으면 점수 + 1
    5. 비밀번호의 길이가 6자 이상이면 점수 + 1
    6. 비밀번호가 들어가 있지 않으면 ( 빈값이면 ), 점수는 -1 이며 Error 표기
    */
    private enum class PasswordLevelTest(
        val password: String,
        val expectedRating: Int
    ) {
        EMPTY_PASSWORD("", TextUtils.EMPTY_PASSWORD),
        SHORT_LOWER_PASSWORD("asdf", 1),
        SHORT_UPPER_PASSWORD("ASDF", 1),
        SHORT_LOWER_UPPER_PASSWORD("asdfA", 2),
        SHORT_LOWER_UPPER_SPECIAL_CHAR_PASSWORD("asdF@", 3),
        SHORT_LOWER_UPPER_SPECIAL_CHAR_NUMBER_PASSWORD("asD@1", 4),
        LONG_LOWER_PASSWORD("asdfasdf", 2),
        LONG_UPPER_PASSWORD("ASDFASDF", 2),
        LONG_LOWER_UPPER_PASSWORD("asdfASDF", 3),
        LONG_LOWER_UPPER_SPECIAL_CHAR_PASSWORD("asdfASDF@", 4),
        LONG_LOWER_UPPER_SPECIAL_CHAR_NUMBER_PASSWORD("asdfASDF@123", 5),
        HAS_KOREAN_CHAR("안녕하세요", 1)
    }

    @Test
    fun testForEmptyPassword() {
        assertEquals(
            PasswordLevelTest.EMPTY_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.EMPTY_PASSWORD.password)
        )
    }

    @Test
    fun testForShortLowerPassword() {
        assertEquals(
            PasswordLevelTest.SHORT_LOWER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.SHORT_LOWER_PASSWORD.password)
        )
    }

    @Test
    fun testForShortUpperPassword() {
        assertEquals(
            PasswordLevelTest.SHORT_UPPER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.SHORT_UPPER_PASSWORD.password)
        )
    }

    @Test
    fun testForShortLowerUpperPassword() {
        assertEquals(
            PasswordLevelTest.SHORT_LOWER_UPPER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.SHORT_LOWER_UPPER_PASSWORD.password)
        )
    }

    @Test
    fun testForShortLowerUpperSpecialCharPassword() {
        assertEquals(
            PasswordLevelTest.SHORT_LOWER_UPPER_SPECIAL_CHAR_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.SHORT_LOWER_UPPER_SPECIAL_CHAR_PASSWORD.password)
        )
    }

    @Test
    fun testForShortLowerUpperSpecialCharNumberPassword() {
        assertEquals(
            PasswordLevelTest.SHORT_LOWER_UPPER_SPECIAL_CHAR_NUMBER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.SHORT_LOWER_UPPER_SPECIAL_CHAR_NUMBER_PASSWORD.password)
        )
    }

    @Test
    fun testForLongLowerPassword() {
        assertEquals(
            PasswordLevelTest.LONG_LOWER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.LONG_LOWER_PASSWORD.password)
        )
    }

    @Test
    fun testForLongUpperPassword() {
        assertEquals(
            PasswordLevelTest.LONG_UPPER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.LONG_UPPER_PASSWORD.password)
        )
    }

    @Test
    fun testForLongLowerUpperPassword() {
        assertEquals(
            PasswordLevelTest.LONG_LOWER_UPPER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.LONG_LOWER_UPPER_PASSWORD.password)
        )
    }

    @Test
    fun testForLongLowerUpperSpecialCharPassword() {
        assertEquals(
            PasswordLevelTest.LONG_LOWER_UPPER_SPECIAL_CHAR_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.LONG_LOWER_UPPER_SPECIAL_CHAR_PASSWORD.password)
        )
    }

    @Test
    fun testForLongLowerUpperSpecialCharNumberPassword() {
        assertEquals(
            PasswordLevelTest.LONG_LOWER_UPPER_SPECIAL_CHAR_NUMBER_PASSWORD.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.LONG_LOWER_UPPER_SPECIAL_CHAR_NUMBER_PASSWORD.password)
        )
    }

    @Test
    fun testForHasKoreanChar() {
        assertEquals(
            PasswordLevelTest.HAS_KOREAN_CHAR.expectedRating,
            TextUtils.getPasswordRating(PasswordLevelTest.HAS_KOREAN_CHAR.password)
        )
    }
}