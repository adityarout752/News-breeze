package com.example.newsbreeze

import NewsAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newsbreeze.databinding.ActivityMainBinding
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.utils.onItemClicked
import com.example.newsbreeze.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), onItemClicked {
    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsViewModel: NewsViewModel
    val position: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setViewModel()
        setDataRv()
        openSavedNews()
        searchText()

    }

    private fun setViewModel() {
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    private fun openSavedNews() {
        binding.saveView.setOnClickListener {
            val intent = Intent(this@MainActivity, SavedNewsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setDataRv() {
        newsViewModel.getBreakingNews("us")
        newsViewModel.newsRespone.observe(this) { remote ->


            newsAdapter = NewsAdapter(this, remote.data!!, this)
            binding.rvNews.adapter = newsAdapter
            newsAdapter.notifyDataSetChanged()
        }
    }

    private fun searchText() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newsAdapter.getFilter()?.filter(newText)

                return true
            }

        })
        binding.searchView.setOnCloseListener { //when canceling the search
            setDataRv()
            true
        }
        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                binding.searchView.isIconified = true
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.searchView.isIconified) {
            binding.searchView.onActionViewCollapsed()
        } else {
            super.onBackPressed()
        }
    }

    override fun readData(article: Article) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("data", article)
        startActivity(intent)
    }

    override fun saveData(article: Article) {
        newsViewModel.insertNews(article)

    }


}