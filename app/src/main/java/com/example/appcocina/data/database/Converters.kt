/*package com.example.appcocina.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromString(value: String?): MutableList<String> {
            val listType: Type = object : TypeToken<MutableList<String?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromArrayList(list: MutableList<String>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}*/


