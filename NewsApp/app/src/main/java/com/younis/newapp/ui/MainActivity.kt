package com.younis.newapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.younis.newapp.R
import com.younis.newapp.data.remote.NewsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_nav)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.findNavController()
        bottomNavigationView.setupWithNavController(navController)

//        val retrofit =
//                Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .build()
//        val service = retrofit.create(NewsApi::class.java)
//        disposable=service.getBreakingNews("us","business","bdc695c2d6c64e9a82a711dc5575b9d1")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    Toast.makeText(this,it.articles.toString(),Toast.LENGTH_SHORT).show()
//                }

        //appBarConfiguration = AppBarConfiguration(setOf(androidx.navigation.ui.R.id.breakingNewsFragment))
    }






    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}