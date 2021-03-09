package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userinfo")

data class UserEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name="email")
    val email:String

)