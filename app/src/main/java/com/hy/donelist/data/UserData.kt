package com.hy.donelist.data

data class UserData(
    var numberCount: Int = 0,
    var currentContent: DoneListData = DoneListData(date = "", allCount = 0, doneContent = arrayListOf()),
    var doneList: List<DoneListData> = listOf()
)
