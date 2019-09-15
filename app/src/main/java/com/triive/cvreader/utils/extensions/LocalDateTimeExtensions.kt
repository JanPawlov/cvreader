package com.triive.cvreader.utils.extensions

import com.triive.cvreader.utils.DateFormat
import org.threeten.bp.LocalDateTime

fun LocalDateTime.formatDate(dateFormat: DateFormat = DateFormat.Date): String = format(dateFormat.formatter)
