package com.example.myapplication

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository( private val userDOA: UserDOA ) {

    val allUsers: LiveData<List<UserEntity>> = userDOA.getAllUserInfo()!!

    fun insertUser(user:UserEntity){
        userDOA.insertUser(user)
    }

}