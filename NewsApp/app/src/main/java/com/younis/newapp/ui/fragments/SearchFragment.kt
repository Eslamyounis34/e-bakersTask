package com.younis.newapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.younis.newapp.R
import com.younis.newapp.data.remote.RetroInstance
import com.younis.newapp.model.Article
import com.younis.newapp.model.Constants
import com.younis.newapp.model.Constants.Companion.API_KEY
import com.younis.newapp.repositories.SearchNewsRepository
import com.younis.newapp.ui.adapters.NewsAdapter
import com.younis.newapp.viewmodel.BreakingNewsViewModel
import com.younis.newapp.viewmodel.SerachNewsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class SearchFragment : Fragment() {

    lateinit var viewModel: SerachNewsViewModel
    lateinit var searchRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        searchRecyclerView = v.findViewById(R.id.searchrecyclerview)

        viewModel = ViewModelProvider(this).get(SerachNewsViewModel::class.java)
        viewModel.getSearchedArticles().observe(viewLifecycleOwner, Observer {
            searchRecyclerView.apply {
                adapter = NewsAdapter(it)
            }
        })
        return v
    }
}