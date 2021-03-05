package activitytest.example.com.roomdemo.home.adapter

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.BlogItemCardBinding
import activitytest.example.com.roomdemo.home.adapter.BlogAdapter.BlogInnerView
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce
import activitytest.example.com.roomdemo.main.activity.WebViewActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val context: Context, private val blogIntroduceList: List<BlogIntroduce>) : RecyclerView.Adapter<BlogInnerView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogInnerView {
        val viewDataBinding: BlogItemCardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.blog_item_card, parent, false)
        return BlogInnerView(viewDataBinding)
    }

    override fun onBindViewHolder(holder: BlogInnerView, position: Int) {
        holder.blogItemCardBinding.blogTags.text = blogIntroduceList[position].tag
        holder.blogItemCardBinding.blogTitle.text = blogIntroduceList[position].blogTitle
        holder.blogItemCardBinding.blogContent.text = blogIntroduceList[position].time
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("html", blogIntroduceList[position].html)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return blogIntroduceList.size
    }

    class BlogInnerView(var blogItemCardBinding: BlogItemCardBinding) : RecyclerView.ViewHolder(blogItemCardBinding.root)
}