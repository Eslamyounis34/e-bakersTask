package com.example.nightmode

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var darkSwitch=findViewById<View>(R.id.darkmodebutton) as SwitchCompat
        var sharedPreferences=getSharedPreferences("night",0)
        val value:Boolean=sharedPreferences!!.getBoolean("night_mode",true)
//        if (value){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            darkSwitch.isChecked=true
//        }

        if (value){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            darkSwitch.isChecked=true
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkSwitch.isChecked=false
        }


//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        darkSwitch.isChecked=false



        darkSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                darkSwitch.isChecked=true
                val editor: SharedPreferences.Editor=sharedPreferences.edit()
                editor.putBoolean("night_mode",true)
                editor.commit()
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                darkSwitch.isChecked=false
                val editor: SharedPreferences.Editor=sharedPreferences.edit()
                editor.putBoolean("night_mode",false)
                editor.commit()
            }
        })

    }
}