package com.hy.donelist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "DoneListTable")
@TypeConverters(DoneContentDataConvert::class)
data class DoneListData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "all_count")
    var allCount: Int = 0,

    @ColumnInfo(name = "done_content")
    var doneContent: ArrayList<String> = arrayListOf()
)