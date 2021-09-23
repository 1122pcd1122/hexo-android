package activitytest.example.com.home_module.home.adapter

import activitytest.example.com.home_module.R
import activitytest.example.com.home_module.databinding.HomeCardItemBinding
import activitytest.example.com.home_module.home.adapter.BlogAdapter.BlogInnerView
import activitytest.example.com.home_module.home.bean.ListArticle
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val click: (String) -> Unit) : PagingDataAdapter<ListArticle,BlogInnerView>(COMPARATOR) {


    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<ListArticle>() {
            override fun areItemsTheSame(oldItem: ListArticle, newItem: ListArticle): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ListArticle, newItem: ListArticle): Boolean {
                return oldItem.title == newItem.title
            }
        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogInnerView {
        val viewDataBinding: HomeCardItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.home_card_item, parent, false)
        return BlogInnerView(viewDataBinding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BlogInnerView, position: Int) {
        val articles = getItem(position)
//        //标签
//        holder.blogItemCardBinding.blogTags.text = articles?.tags

        //标题
        holder.blogItemCardBinding.blogTitle.text = articles?.title

//        //时间
        holder.blogItemCardBinding.blogContent.text = articles?.month +" "+ (articles?.day?.get(0) ?: "")+" "+articles?.year

        //进入webView
        holder.itemView.setOnClickListener {

            articles?.href?.let { html -> click(html) }
        }
    }


    class BlogInnerView(var blogItemCardBinding: HomeCardItemBinding) : RecyclerView.ViewHolder(blogItemCardBinding.root)
}