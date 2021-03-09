package com.example.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class WelcomeFragment : Fragment() {

private val args:WelcomeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v= inflater.inflate(R.layout.fragment_welcome, container, false)
        val username_tx=v.findViewById<TextView>(R.id.usernmae_welcome) as TextView
        val password_tx=v.findViewById<TextView>(R.id.password_welcome) as TextView
        val okButton=v.findViewById<Button>(R.id.okBt) as Button

        username_tx.setText(args.username)
        password_tx.setText(args.password)

        okButton.setOnClickListener {
            val action=WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment()
            findNavController().navigate(action)
        }


        return v
    }


}