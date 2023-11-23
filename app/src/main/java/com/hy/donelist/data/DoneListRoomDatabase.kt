package com.hy.donelist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// when schema is edited, update version
@Database(entities = [DoneListData::class], version = 1, exportSchema = false)
abstract class DoneListRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): DoneListDao

    companion object {
        @Volatile
        private var INSTANCE: DoneListRoomDatabase? = null

        fun getDatabase(context: Context): DoneListRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoneListRoomDatabase::class.java,
                    "donelist_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}