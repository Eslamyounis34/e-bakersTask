package com.example.myapplication

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.processor.Context


@Database(entities = [UserEntity::class],version = 1)
 abstract class RoomAppDB : RoomDatabase() {

    abstract fun userDao(): UserDOA?

    companion object {
        private var INSTANCE: RoomAppDB? = null

        fun getAppDataBase(context: android.content.Context): RoomAppDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppDB>(
                    context.applicationContext, RoomAppDB::class.java, "AppDBB"
                )
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
    }


}


