package com.apptimizm.mgs.datasource.model.route

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types



/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-19
 * Time: 14:04
 */

class BugTypeConverter {

    @TypeConverter
    fun fromBugList(value: List<BugEntity>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, BugEntity::class.java)
        val adapter: JsonAdapter<List<BugEntity>> = moshi.adapter(type)
        val bugs = adapter.toJson(value)
        return bugs
    }

    @TypeConverter
    fun toBugList(value: String): List<BugEntity>? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, BugEntity::class.java)
        val adapter: JsonAdapter<List<BugEntity>> = moshi.adapter(type)
        val bugs = adapter.fromJson(value)
        return  bugs

    }
}