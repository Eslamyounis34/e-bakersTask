package com.younis.e_bakerstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.younis.e_bakerstask.R
import com.younis.e_bakerstask.viewmodel.CartViewModel
import com.younis.e_bakerstask.viewmodel.CheckOutViewModel

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val intent = intent.getStringExtra("CartData").toString()
        var delivery_cost=5.00

        var total_cost=intent.toDouble()+delivery_cost

        lateinit var viewModel: CheckOutViewModel
        lateinit var fullname:TextView
        lateinit var phone:TextView
        lateinit var streetnumber:TextView
        lateinit var building_no:TextView
        lateinit var floor_no:TextView
        lateinit var total_cost_tx:TextView
        lateinit var checkout_totalprice_tx:TextView
        lateinit var progressBar: ProgressBar
        progressBar = findViewById(R.id.check_out_progress_bar)



        fullname=findViewById(R.id.user_fullname)
        phone=findViewById(R.id.user_phone)
        streetnumber=findViewById(R.id.user_street)
        building_no=findViewById(R.id.user_buildingNo)
        floor_no=findViewById(R.id.user_floorNo)
        total_cost_tx=findViewById(R.id.checkout_total_cost_tx)
        checkout_totalprice_tx=findViewById(R.id.checkout_totalprice_tx)

        progressBar.visibility = View.VISIBLE
        checkout_totalprice_tx.setText(intent)
        total_cost_tx.setText(total_cost.toString())


        viewModel= ViewModelProvider(this).get(CheckOutViewModel::class.java)
        viewModel.getCartData().observe(this, Observer {

            if (it.customer_home_number==null){
                building_no.setText("")
            }else{
                building_no.setText(it.customer_home_number)
            }
            if (it.customer_floor_number==null){
                floor_no.setText("")
            }else{
                floor_no.setText(it.customer_home_number)
            }
            if (it.customer_street==null){
                streetnumber.setText("")
            }
            else{
                streetnumber.setText(it.customer_street)

            }
            progressBar.visibility=View.GONE
            fullname.setText(it.full_name)
            phone.setText(it.phone)

        })
    }
}