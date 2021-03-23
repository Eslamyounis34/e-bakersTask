package com.younis.e_bakerstask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.younis.e_bakerstask.model.AddToCartResponse
import com.younis.e_bakerstask.model.CartResponse
import com.younis.e_bakerstask.model.Product
import com.younis.e_bakerstask.repositories.CartRepository

class CartViewModel(application: Application): AndroidViewModel(application) {

    private val cartrepository = CartRepository(application)

    val cartList: LiveData<List<Product>>
     lateinit var  addtoCartResponse:LiveData<AddToCartResponse>

    init {
        this.cartList = cartrepository.getCartData()
    }

    fun getCartData():LiveData<List<Product>>{
        return cartList
    }
    fun getCartAddtionData(product_id:Int,product_qty:Double):LiveData<AddToCartResponse>{
      //  if(::addtoCartResponse.isInitialized.not() ){
            addtoCartResponse=cartrepository.getCaraAddData(product_id,product_qty)
      //  }
        return addtoCartResponse
    }


}