package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDOA {

    @Query("SELECt * From userinfo ORDER BY id DESC")
    fun getAllUserInfo():LiveData<List<UserEntity>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:UserEntity)

    @Delete
    fun deleteUser(user:UserEntity)

    @Update
    fun updateUser(user:UserEntity)
}