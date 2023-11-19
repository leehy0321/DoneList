package com.hy.donelist.data

data class DoneListData(
    var date: String = "",
    var allCount: Int = 0,
    var doneContent: ArrayList<String> = arrayListOf()
)