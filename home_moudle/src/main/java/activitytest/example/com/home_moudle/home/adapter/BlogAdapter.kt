package activitytest.example.com.home_moudle.home.adapter

import activitytest.example.com.home_moudle.R
import activitytest.example.com.home_moudle.WebViewActivity
import activitytest.example.com.home_moudle.databinding.HomeCardItemBinding
import activitytest.example.com.home_moudle.home.adapter.BlogAdapter.BlogInnerView
import activitytest.example.com.home_moudle.home.bean.ArticleBean
import activitytest.example.com.home_moudle.home.bean.Articles
import activitytest.example.com.home_moudle.home.utils.MonthUtil
import activitytest.example.com.home_moudle.home.utils.TagsUtil
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val context: Context, private val articleBean: ArticleBean) : RecyclerView.Adapter<BlogInnerView>() {

    private val articles: List<Articles> = articleBean.articles


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogInnerView {
        val viewDataBinding: HomeCardItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.home_card_item, parent, false)
        return BlogInnerView(viewDataBinding)
    }


    override fun onBindViewHolder(holder: BlogInnerView, position: Int) {
        val articles = articles[position]
        //标签
        holder.blogItemCardBinding.blogTags.text = articles.tags.let { TagsUtil.getTag(it) }

        //标题
        holder.blogItemCardBinding.blogTitle.text = articles.title

        //时间
        "${articles.month.let { MonthUtil.getMonthEN(it) }} ${articles.day} ${articles.year}年".also { holder.blogItemCardBinding.blogContent.text = it }

        //进入webView
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("html", articles.htmlUrl)
            context.startActivity(intent)
        }
    }

    /*
        获取项目数
     */
    override fun getItemCount(): Int {

        return articleBean.articles.size
    }

    class BlogInnerView(var blogItemCardBinding: HomeCardItemBinding) : RecyclerView.ViewHolder(blogItemCardBinding.root)
}