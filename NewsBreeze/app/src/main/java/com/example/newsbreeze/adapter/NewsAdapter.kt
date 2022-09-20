import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsbreeze.databinding.BreakingNewsItemLayoutBinding
import com.example.newsbreeze.model.Article
import com.example.newsbreeze.model.NewsResponse
import com.example.newsbreeze.utils.onItemClicked
import java.util.*

class NewsAdapter(
    val context: Context,
    val newsResponse: NewsResponse,
    val onItemClickedListener: onItemClicked,
) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: BreakingNewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            BreakingNewsItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        val newsResponseList = newsResponse.articles[position]
        binding.ivBreaking.load(newsResponseList.urlToImage) {
            crossfade(true)
        }
        binding.tvTitle.text = newsResponseList.title
        binding.tvDesc.text = newsResponseList.description
        binding.tvDate.text = newsResponseList.publishedAt

        binding.root.setOnClickListener {
            onItemClickedListener.readData(newsResponseList)
        }
        binding.tvRead.setOnClickListener {
            onItemClickedListener.readData(newsResponseList)
        }
        binding.tvSave.setOnClickListener {
            onItemClickedListener.saveData(newsResponseList)
        }


    }

    override fun getItemCount(): Int {
        return newsResponse.articles.size
    }

    fun getFilter(): Filter? {
        return Searched_Filter
    }

    private val Searched_Filter: Filter = object : Filter() {
        protected override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: ArrayList<Article> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(newsResponse.articles)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in newsResponse.articles) {
                    if (item.title!!.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            newsResponse.articles.clear()
            newsResponse.articles.addAll(results.values as Collection<Article>)
            notifyDataSetChanged()
        }
    }


}