package com.code.jamie.noteme.room

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

object Converters {
    @TypeConverter
    fun dateToString(date: Long) =
        SimpleDateFormat(
            "yyyy/MM/dd",
            Locale.getDefault()
        ).format(date)

    @TypeConverter
    fun StringToDate(date: String) =
        SimpleDateFormat(
            "yyyy/MM/dd",
            Locale.getDefault()
        ).parse(date)?.time
}