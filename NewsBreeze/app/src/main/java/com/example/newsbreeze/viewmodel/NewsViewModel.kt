package com.example.newsbreeze.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.model.NewsResponse
import com.example.newsbreeze.remote.NetworkResult
import com.example.newsbreeze.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {


    //region Remote
    var newsRespone: MutableLiveData<NetworkResult<NewsResponse>> = MutableLiveData()


    fun getBreakingNews(country: String) = viewModelScope.launch {
        try {
            val response = repository.remote.getBreakingNews(country)
            newsRespone.value = handleNewsResponse(response)


        } catch (e: Exception) {
            newsRespone.value = NetworkResult.Error("News Not Found")
        }
    }

    private fun handleNewsResponse(response: Response<NewsResponse>): NetworkResult<NewsResponse> {

        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }


            response.isSuccessful -> {
                val newsResponse = response.body()
                return NetworkResult.Success(newsResponse!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }
    //endregion

    //region Database
    val readDbNews: LiveData<List<Article>> = repository.local.readNews().asLiveData()

    fun insertNews(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertNews(article)
        }
    }
    //endregion
}