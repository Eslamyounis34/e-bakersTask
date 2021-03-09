package com.example.myapplication;
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel( application: Application ): AndroidViewModel(application) {

    private val  repository:UserRepository

    val allUSERS: LiveData<List<UserEntity>>


    init {
        val userDao = RoomAppDB.getAppDataBase(application)!!.userDao()
        repository = UserRepository(userDao!!)
        allUSERS = repository.allUsers
    }


    fun getAllUsers( ):LiveData<List<UserEntity>>
    {
        return allUSERS

    }

    fun insertUser(user:UserEntity){
        repository.insertUser(user)
    }

}