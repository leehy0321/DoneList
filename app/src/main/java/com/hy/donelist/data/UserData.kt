package com.hy.donelist.data

data class UserData(
    var id : String = "",
    var doneList : List<DoneListData> = listOf()
)
