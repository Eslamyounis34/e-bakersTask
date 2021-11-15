package com.younis.newapp.ui.fragments

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.younis.newapp.R
import com.younis.newapp.data.remote.NewsApi
import com.younis.newapp.model.Article
import com.younis.newapp.model.ConnectionLiveData
import com.younis.newapp.model.OnArticleListner
import com.younis.newapp.ui.MainActivity
import com.younis.newapp.ui.adapters.NewsAdapter
import com.younis.newapp.viewmodel.BreakingNewsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_breaking_news_fragments.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class BreakingNewsFragment : Fragment(), OnArticleListner {

    private lateinit var cld : ConnectionLiveData

    private lateinit var layout1 : RelativeLayout
    private lateinit var layout2 : RelativeLayout


    lateinit var viewModel: BreakingNewsViewModel
    private val pgadapter = NewsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

         var v = inflater.inflate(R.layout.fragment_breaking_news_fragments, container, false)


        if (isNetworkAvailable()) {

            viewModel = ViewModelProvider(this).get(BreakingNewsViewModel::class.java)
             // viewModel=(activity as MainActivity).viewModel

            var darkSwitch = v.findViewById<View>(R.id.mode_switcher) as SwitchCompat
            var sharedPreferences = activity?.getSharedPreferences("night", 0)
            val value: Boolean = sharedPreferences!!.getBoolean("night_mode", true)
            if (value) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                darkSwitch.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                darkSwitch.isChecked = false

            }

            darkSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    darkSwitch.isChecked = true
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putBoolean("night_mode", true)
                    editor.commit()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    darkSwitch.isChecked = false
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putBoolean("night_mode", false)
                    editor.commit()
                }
            })

            viewModel.articlePagedList.observe(viewLifecycleOwner, Observer {
                pgadapter.submitList(it)
                Log.e("TestBreakingNewsList",it.toString())
                breakingnewsrecyclerview.apply {
                    adapter = pgadapter
                    pgadapter.setOnItemClickListener(this@BreakingNewsFragment)
                }
            })

            return v
        } else {
            v = inflater.inflate(R.layout.no_internet_connection, container, false)
            return v
        }
    }

    override fun onclick(article: Article) {
        val action =
            BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(article)
        findNavController()
            .navigate(action)
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(application = Application())

        cld.observe(this, { isConnected ->

            if (isConnected){

                layout1.visibility = View.VISIBLE
                layout2.visibility = View.GONE

            }else{
                layout1.visibility = View.GONE
                layout2.visibility = View.VISIBLE
            }

        })
    }
}