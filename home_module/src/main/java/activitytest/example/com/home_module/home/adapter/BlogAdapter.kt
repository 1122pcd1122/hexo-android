package activitytest.example.com.home_module.home.adapter

import activitytest.example.com.componentbase.ContentServiceFactory
import activitytest.example.com.home_module.R
import activitytest.example.com.home_module.databinding.HomeCardItemBinding
import activitytest.example.com.home_module.home.adapter.BlogAdapter.BlogInnerView
import activitytest.example.com.home_module.home.bean.ListArticle
import activitytest.example.com.home_module.home.utils.MonthUtil
import activitytest.example.com.home_module.home.utils.TagsUtil
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val context: Context,private val activity: Activity?, private val listArticle: List<ListArticle>) : RecyclerView.Adapter<BlogInnerView>() {

    private val articles: List<ListArticle> = listArticle


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
        "${articles.month.let {
            MonthUtil.getMonthEN(it)
        }} ${articles.day} ${articles.year}年".also { holder.blogItemCardBinding.blogContent.text = it }

        //进入webView
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("html",articles.htmlUrl)
            if (activity != null) {
                ContentServiceFactory.getFragmentService()?.startActivity(context,activity,bundle,"进入WebActivity")
            }
        }
    }

    /*
        获取项目数
     */
    override fun getItemCount(): Int {
        return listArticle.size
    }

    class BlogInnerView(var blogItemCardBinding: HomeCardItemBinding) : RecyclerView.ViewHolder(blogItemCardBinding.root)
}