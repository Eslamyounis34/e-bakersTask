package com.example.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController


class LoginFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_login, container, false)
        val userName=v.findViewById<EditText>(R.id.username) as EditText
        val password=v.findViewById<EditText>(R.id.password) as EditText
        val confirmBt=v.findViewById<EditText>(R.id.confirm)as Button

        confirmBt.setOnClickListener {
            val username_text=userName.text.toString()
            val password_text=password.text.toString()

            val action=LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username_text,password_text)
            findNavController().navigate(action)

        }


        return v
    }

}