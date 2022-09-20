package com.example.newsbreeze

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.newsbreeze.databinding.ActivityDetailsBinding
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityDetailsBinding
    lateinit var dataArticle: Article
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        setClick()
        setViewModel()
        getDataFromIntent()
        setDataUI()
    }

    private fun setClick() {
        binding.back.setOnClickListener(this)
        binding.tvSaving.setOnClickListener(this)
    }

    private fun setViewModel() {
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    private fun setDataUI() {
        binding.ivNews.load(dataArticle.urlToImage)
        binding.title.text = dataArticle.title
        binding.date.text = dataArticle.publishedAt
        binding.tvAuthorName.text = dataArticle.author
        binding.tvContent.text = dataArticle.content
    }

    private fun getDataFromIntent() {
        dataArticle = intent.getParcelableExtra("data")!!
        Log.d("article", "$dataArticle")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.back -> {
                finish()
            }
            R.id.tv_saving -> {
                newsViewModel.insertNews(dataArticle)

            }
        }
    }
}