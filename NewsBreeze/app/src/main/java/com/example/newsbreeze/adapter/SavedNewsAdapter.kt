import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsbreeze.databinding.SavedNewsItemLayoutBinding
import com.example.newsbreeze.model.Article
import java.util.*

class SavedNewsAdapter(
    val context: Context,
    val article: MutableList<Article>
    ) :
    RecyclerView.Adapter<SavedNewsAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: SavedNewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SavedNewsItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        val listData = article[position]
        binding.tvDate.text = listData.publishedAt
        binding.tvTitle.text = listData.title
        binding.tvAuthor.text = listData.author
        binding.ivShape.load(listData.urlToImage)
        binding.tvNewstype.text = listData.source!!.name


    }

    override fun getItemCount(): Int {
        return article.size
    }
    fun getFilter(): Filter {
        return Searched_Filter
    }

    private val Searched_Filter: Filter = object : Filter() {
        protected override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: ArrayList<Article> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(article)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in article) {
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
            article.clear()
            article.addAll(results.values as Collection<Article>)
            notifyDataSetChanged()
        }
    }

}