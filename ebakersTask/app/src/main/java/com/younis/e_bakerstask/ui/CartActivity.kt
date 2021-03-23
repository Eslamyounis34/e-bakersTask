package com.younis.e_bakerstask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.younis.e_bakerstask.R
import com.younis.e_bakerstask.ui.adapters.CartRecyclerAdapter
import com.younis.e_bakerstask.viewmodel.CartViewModel

class CartActivity : AppCompatActivity(), CartRecyclerAdapter.ClickListner {
    lateinit var viewModel: CartViewModel
    lateinit var totalTx: TextView
    lateinit var total_cost: String
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        lateinit var cartRecycler: RecyclerView
        lateinit var checkOutBt: Button
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE


        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartRecycler = findViewById(R.id.cartListRecycler)
        checkOutBt = findViewById(R.id.checkout_bt)
        totalTx = findViewById(R.id.totalresulttx)



        viewModel.getCartData().observe(this, Observer {

            cartRecycler.apply {
                progressBar.visibility = View.GONE
                adapter = CartRecyclerAdapter(it, this@CartActivity)

            }
            total_cost = it[0].ProductInCartTotal.toString()
            totalTx.setText(it[0].ProductInCartTotal.toString())

        })

        checkOutBt.setOnClickListener {
            var intent = Intent(this, CheckOutActivity::class.java)
            intent.putExtra("CartData", total_cost)
            startActivity(intent)
        }
    }


    override fun onClicked(qty: Double, product_ID: Int, total_amount: Double) {
        progressBar.visibility = View.VISIBLE

        total_cost = total_amount.toString()
        totalTx.setText(total_amount.toString())


        viewModel.getCartAddtionData(product_ID, qty).observe(this, Observer {
            progressBar.visibility = View.GONE
            Toast.makeText(this, it.success.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}