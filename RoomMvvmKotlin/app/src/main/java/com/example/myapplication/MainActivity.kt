package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Dao
import androidx.room.Delete
import java.util.List.of

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:MainActivityViewModel
    lateinit var nameInput:EditText
    lateinit var emailInput:EditText
    lateinit var phoneInput:EditText
    lateinit var saveBt:Button
    lateinit var strUsername: String
    lateinit var strEmail: String
    lateinit var strPhone: String
    lateinit var  recyclerview:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput=findViewById(R.id.nameedittext)
        emailInput=findViewById(R.id.emailedittext)
        phoneInput=findViewById(R.id.phoneedittext)
        saveBt=findViewById(R.id.saveButton)
        recyclerview=findViewById(R.id.recyclerView)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        saveBt.setOnClickListener {
            strUsername=nameInput.text.toString()
            strEmail=emailInput.text.toString()
            strPhone=phoneInput.text.toString()


            if(strPhone.isEmpty()||strEmail.isEmpty()||strUsername.isEmpty())
            {
                Toast.makeText(this,"Fill All Data",Toast.LENGTH_SHORT).show()
            }else
            {
                val user=UserEntity(0,strUsername,strEmail)
                viewModel.insertUser(user)
            }

        }

        viewModel.getAllUsers().observe(this, Observer {

            recyclerview.apply {
                adapter=UserAdapter(it)
            }
        })

    }
}