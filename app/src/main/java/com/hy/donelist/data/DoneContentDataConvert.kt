package com.hy.donelist.data

import androidx.room.TypeConverter

class DoneContentDataConvert {
    @TypeConverter
    fun convertArrayToString(value: ArrayList<String>): String {
        var stringValue: String = value[0]
        for (i in 1 until value.size) {
            stringValue = buildString {
                append(stringValue)
                append(";")
                append(value[i])
            }
        }
        return stringValue
    }

    @TypeConverter
    fun convertStringToArray(value: String): ArrayList<String> =
        ArrayList(value.split(";"))
}