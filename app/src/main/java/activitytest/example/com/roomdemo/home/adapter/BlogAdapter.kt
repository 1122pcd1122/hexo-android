package activitytest.example.com.roomdemo.home.adapter

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.HomeCardItemBinding
import activitytest.example.com.roomdemo.home.adapter.BlogAdapter.BlogInnerView
import activitytest.example.com.roomdemo.home.utils.MonthUtil
import activitytest.example.com.roomdemo.home.utils.TagsUtil
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce
import activitytest.example.com.roomdemo.main.activity.WebViewActivity
import activitytest.example.com.roomdemo.main.database.entity.Tags
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val context: Context, private val blogIntroduceList: List<BlogIntroduce>) : RecyclerView.Adapter<BlogInnerView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogInnerView {
        val viewDataBinding: HomeCardItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.home_card_item, parent, false)
        return BlogInnerView(viewDataBinding)
    }

    override fun onBindViewHolder(holder: BlogInnerView, position: Int) {
        val tag = TagsUtil.getTag(blogIntroduceList[position].tags)
        holder.blogItemCardBinding.blogTags.text = tag
        holder.blogItemCardBinding.blogTitle.text = blogIntroduceList[position].blogTitle
        holder.blogItemCardBinding.blogContent.text =  blogIntroduceList[position].time.let { MonthUtil.getNowDate(it) }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("html", blogIntroduceList[position].html)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return blogIntroduceList.size
    }

    class BlogInnerView(var blogItemCardBinding: HomeCardItemBinding) : RecyclerView.ViewHolder(blogItemCardBinding.root)
}