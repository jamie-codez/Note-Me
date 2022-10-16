package com.code.jamie.noteme.room

import java.text.SimpleDateFormat
import java.util.*

object Converters {
    fun dateToString(date: Long) =
        SimpleDateFormat(
            "yyyy/MM/dd",
            Locale.getDefault()
        ).format(date)

    fun StringToDate(date: String) =
        SimpleDateFormat(
            "yyyy/MM/dd",
            Locale.getDefault()
        ).parse(date)?.time
}