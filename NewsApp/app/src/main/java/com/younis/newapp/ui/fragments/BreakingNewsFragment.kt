package com.younis.newapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.younis.newapp.R
import com.younis.newapp.data.remote.NewsApi
import com.younis.newapp.ui.adapters.NewsAdapter
import com.younis.newapp.viewmodel.BreakingNewsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class BreakingNewsFragment : Fragment() {

    lateinit var viewModel: BreakingNewsViewModel
    lateinit var breakingnewsrecyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_breaking_news_fragments, container, false)
        breakingnewsrecyclerview=v.findViewById(R.id.breakingnewsrecyclerview)
        viewModel = ViewModelProvider(this).get(BreakingNewsViewModel::class.java)

        viewModel.getAllArticles().observe(viewLifecycleOwner, Observer {
            breakingnewsrecyclerview.apply {
                adapter = NewsAdapter(it)
            }

            //  Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        })


        return v

    }
}