package com.paulo.urlshortner.common.util

import org.apache.commons.lang3.RandomStringUtils

object RandomAlphanumericUtils {

    fun generate(): String {
        val length = 8
        val useLetters = true
        val useNumbers = true
        return RandomStringUtils.random(length, useLetters, useNumbers)
    }
}