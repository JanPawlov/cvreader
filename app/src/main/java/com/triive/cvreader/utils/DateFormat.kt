package com.triive.cvreader.utils

import org.threeten.bp.format.DateTimeFormatter

sealed class DateFormat {
    abstract val formatter: DateTimeFormatter

    object Date : DateFormat() {
        override val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(DEFAULT_PATTERN)
    }

    companion object {
        private const val DEFAULT_PATTERN = "MM.yyyy"
    }
}

