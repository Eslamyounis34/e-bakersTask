package com.younis.newapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.younis.newapp.R
import com.younis.newapp.model.Article
import com.younis.newapp.model.OnArticleListner
import com.younis.newapp.ui.adapters.NewsAdapter
import com.younis.newapp.ui.adapters.SavedNewsAdapter
import com.younis.newapp.viewmodel.BreakingNewsViewModel
import com.younis.newapp.viewmodel.SavedNewsViewModel
import kotlinx.android.synthetic.main.fragment_saved_news.*


class SavedNewsFragment : Fragment(), OnArticleListner {
    lateinit var viewModel: SavedNewsViewModel
    lateinit var newsAdapter: SavedNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_saved_news, container, false)
        viewModel = ViewModelProvider(this).get(SavedNewsViewModel::class.java)

        viewModel.getSavedArticles().observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){
                savednewsrecycler.visibility= View.GONE
                nofavouritestextid.visibility=View.VISIBLE
            }
            else{
                savednewsrecycler.visibility= View.VISIBLE
                nofavouritestextid.visibility=View.GONE
                savednewsrecycler.apply {
                    adapter = SavedNewsAdapter(it)
                    newsAdapter = adapter as SavedNewsAdapter
                    newsAdapter.setOnItemClickListener(this@SavedNewsFragment)
                }
            }
        })
        return v
    }
    override fun onclick(article: Article) {
        val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(article)
        findNavController().navigate(action)
    }
}