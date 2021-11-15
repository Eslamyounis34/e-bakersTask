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
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.younis.newapp.R
import com.younis.newapp.viewmodel.BreakingNewsViewModel
import kotlin.math.log


class ArticleFragment : Fragment() {


    val args: ArticleFragmentArgs by navArgs()
    lateinit var webView: WebView
    lateinit var fab: FloatingActionButton
    lateinit var viewModel: BreakingNewsViewModel

    @SuppressLint("SetJavaScriptEnabled", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        lateinit var v: View
         var isSaved: Boolean

        if (isNetworkAvailable()) {
            v = inflater.inflate(R.layout.fragment_article, container, false)
            fab = v.findViewById(R.id.floatingbutton)

            viewModel = ViewModelProvider(this).get(BreakingNewsViewModel::class.java)
            webView = v.findViewById(R.id.articlewebview)


            val article = args.article
            Toast.makeText(context, article.url.toString(), Toast.LENGTH_SHORT).show()
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
            Toast.makeText(context, article.id.toString(), Toast.LENGTH_SHORT).show()
            Log.e("TestFavouritebyid",article.id.toString())
            isSaved = viewModel.isArticleSaved(article.url)
            if (isSaved == true) {
                fab.setImageResource(R.drawable.favouriteicon)
            } else {
                fab.setImageResource(R.drawable.no_favorite_icon)

            }
            fab.setOnClickListener {
                if (isSaved == true) {
                    viewModel.deleteArticle(article)
                    fab.setImageResource(R.drawable.no_favorite_icon)
                    isSaved = false
                    Toast.makeText(context, "Article Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertArticle(article)
                    fab.setImageResource(R.drawable.favouriteicon)
                    isSaved = true
                    Toast.makeText(context, "Article Saved", Toast.LENGTH_LONG).show()
                }
            }
            return v
        } else {
            v = inflater.inflate(R.layout.no_internet_connection, container, false)

            return v

        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}

