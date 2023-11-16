package com.hy.donelist.data

data class UserData(
    var numberCount : Int = 0,
    var currentContent : DoneListData = DoneListData("",0, listOf()),
    var doneList : List<DoneListData> = listOf()
)
