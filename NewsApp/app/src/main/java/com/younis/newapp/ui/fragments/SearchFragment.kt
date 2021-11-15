package com.younis.newapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.younis.newapp.R
import com.younis.newapp.model.Article
import com.younis.newapp.model.OnArticleListner
import com.younis.newapp.ui.adapters.NewsAdapter
import com.younis.newapp.viewmodel.SerachNewsViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(), OnArticleListner {
    lateinit var viewModel: SerachNewsViewModel
    lateinit var searchEditText: EditText
    lateinit var searchButton: ImageView
    lateinit var searchRecycler: RecyclerView
    private val pgadapter = NewsAdapter()
    lateinit var noSearchResult: TextView

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var v: View

        if (isNetworkAvailable()) {
            v = inflater.inflate(R.layout.fragment_search, container, false)
            searchEditText = v.findViewById(R.id.searchnewsedittext)
            searchButton = v.findViewById(R.id.searcharticleimagebutton)
            searchRecycler = v.findViewById(R.id.searcharticlesrecyclerview)
            viewModel = ViewModelProvider(this@SearchFragment).get(SerachNewsViewModel::class.java)
            noSearchResult = v.findViewById(R.id.nodatafound)

            searchRecycler.apply {
                adapter = pgadapter
                pgadapter.setOnItemClickListener(this@SearchFragment)
            }
            searchButton.setOnClickListener {
                val query = searchEditText.text.toString()
                viewModel.setListId(query)
                subscribe()
            }
            return v
        } else {
            v = inflater.inflate(R.layout.no_internet_connection, container, false)
            return v
        }
    }

    override fun onclick(article: Article) {
        val action = SearchFragmentDirections.actionSearchFragmentToArticleFragment(article)
        findNavController().navigate(action)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun subscribe() {
        viewModel.articlePagedList.observe(viewLifecycleOwner, Observer { t ->
            if (t.isEmpty()) {
                searchRecycler.visibility = View.INVISIBLE
                noSearchResult.visibility = View.VISIBLE
            } else {
                searchRecycler.visibility = View.VISIBLE
                noSearchResult.visibility = View.INVISIBLE
                Log.e("testSearchList", t.toString())
                pgadapter.submitList(t)
            }
        })
    }
}