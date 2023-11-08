package com.hy.donelist.data

data class UserData(
    var numberCount : Int = 0,
    var doneList : List<DoneListData> = listOf()
)
