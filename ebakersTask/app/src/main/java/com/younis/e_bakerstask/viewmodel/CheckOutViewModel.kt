package com.younis.e_bakerstask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.younis.e_bakerstask.model.Product
import com.younis.e_bakerstask.model.User
import com.younis.e_bakerstask.repositories.CartRepository
import com.younis.e_bakerstask.repositories.CheckOutRepository

class CheckOutViewModel(application: Application): AndroidViewModel(application) {


    private val checkoutrepository = CheckOutRepository(application)

    val profileData: LiveData<User>
    init {
        profileData=checkoutrepository.getProfileData()
    }

    fun getCartData():LiveData<User>{
        return profileData
    }
}