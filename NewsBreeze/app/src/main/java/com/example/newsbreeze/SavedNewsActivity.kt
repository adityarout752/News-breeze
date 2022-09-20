package com.example.newsbreeze

import SavedNewsAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newsbreeze.databinding.ActivitySavedNewsBinding
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySavedNewsBinding
    lateinit var savedNewsAdapter: SavedNewsAdapter
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_saved_news)
        setClick()
        setViewModel()
        setRv()
        searchText()
    }

    private fun setRv() {
        newsViewModel.readDbNews.observe(this) {

            savedNewsAdapter = SavedNewsAdapter(this, it as MutableList<Article>)
            binding.rvSaved.adapter = savedNewsAdapter
            savedNewsAdapter.notifyDataSetChanged()
        }
    }

    private fun setClick() {
        binding.back.setOnClickListener(this)
        binding.tvSeeAll.setOnClickListener(this)
    }

    private fun setViewModel() {
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    private fun searchText() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                savedNewsAdapter.getFilter().filter(newText)

                return true
            }

        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.back -> {
                finish()
            }
            R.id.tv_see_all -> {
                startActivity(Intent(this, MainActivity::class.java))

            }
        }
    }


}