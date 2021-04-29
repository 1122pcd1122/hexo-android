package activitytest.example.com.log_moudle.project.adapter

import activitytest.example.com.log_moudle.R
import activitytest.example.com.log_moudle.WebViewActivity
import activitytest.example.com.log_moudle.databinding.LogCardviewInnercarditemBinding
import activitytest.example.com.log_moudle.project.bean.Articles
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class InnerLogAdapter(private val articleNameList: List<Articles>, private val context: Context): RecyclerView.Adapter<InnerLogAdapter.InnerItem>() {
    class InnerItem(val logCardViewInnerCardItemBinding: LogCardviewInnercarditemBinding):RecyclerView.ViewHolder(logCardViewInnerCardItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerItem {
        val dataBinding = DataBindingUtil.inflate<LogCardviewInnercarditemBinding>(LayoutInflater.from(context), R.layout.log_cardview_innercarditem, parent,false)
        return InnerItem(dataBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InnerItem, position: Int) {
        Log.d("innerRecycleView","内部数据")
        val articles = articleNameList[position]
//        holder.logCardViewInnerCardItemBinding.time.text = "${articles.month} ${articles.day}"
        holder.logCardViewInnerCardItemBinding.title.text = articles.title
        holder.logCardViewInnerCardItemBinding.root.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("html", articles.htmlUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return articleNameList.size
    }
}


