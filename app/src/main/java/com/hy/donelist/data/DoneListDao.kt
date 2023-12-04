package com.hy.donelist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DoneListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // suspend : to execute this function in the other thread because this function could be taken long time.
    suspend fun insert(item: DoneListData)

    @Delete
    suspend fun delete(item: DoneListData)

    // Flow / LiveData : to get the alarm whenever the data is changed.
    @Query("SELECT * from DoneListTable WHERE date = :id")
    fun getItem(id: String): Flow<DoneListData>

    @Query("SELECT * from DoneListTable ORDER BY date DESC")
    fun getItems(): Flow<List<DoneListData>>
}