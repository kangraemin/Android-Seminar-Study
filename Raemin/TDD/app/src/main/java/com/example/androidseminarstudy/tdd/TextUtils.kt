package com.example.androidseminarstudy.tdd

import java.util.*
import java.util.regex.Pattern

object TextUtils {
    fun getPasswordRating(password: String): Int {
        if (password.isEmpty()) {
            return EMPTY_PASSWORD
        }

        var passwordRatingResult = 1

        if (isLongPassword(password)) {
            passwordRatingResult++
        }

        if (hasMixedCaseCharacter(password)) {
            passwordRatingResult++
        }

        var calculatedForSpecialCharacter = false
        var calculatedForNumberCharacter = false

        password.forEach {
            if (it.isDigit() && !calculatedForNumberCharacter) {
                passwordRatingResult++
                calculatedForNumberCharacter = true
            }
            if (isSpecialCharacter(it) && !calculatedForSpecialCharacter) {
                passwordRatingResult++
                calculatedForSpecialCharacter = true
            }
        }

        return passwordRatingResult
    }

    private fun isLongPassword(password: String) = password.length >= 6

    private fun hasMixedCaseCharacter(text: String) =
        text != text.toUpperCase(Locale.getDefault()) && text != text.toLowerCase(Locale.getDefault())

    private fun isSpecialCharacter(character: Char): Boolean {
        return Pattern.matches(
            "[!\"#$%&'()*+,.:;<=>?@^_`{|}~\\-\\/\\[\\\\\\]]+",
            character.toString()
        )
    }

    const val EMPTY_PASSWORD = -1
}