package activitytest.example.com.roomdemo.project.adapter

import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.ProjectCardviewInnercarditemBinding
import activitytest.example.com.roomdemo.home.utils.MonthUtil
import activitytest.example.com.roomdemo.main.database.entity.Blog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class InnerLogAdapter(private val mutableList : MutableList<Blog>?, private val context: Context): RecyclerView.Adapter<InnerLogAdapter.InnerItem>() {
    class InnerItem(val projectCardViewInnerCardItemBinding: ProjectCardviewInnercarditemBinding):RecyclerView.ViewHolder(projectCardViewInnerCardItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerItem {
        val dataBinding = DataBindingUtil.inflate<ProjectCardviewInnercarditemBinding>(LayoutInflater.from(context), R.layout.project_cardview_innercarditem, parent,false)
        return InnerItem(dataBinding)
    }

    override fun onBindViewHolder(holder: InnerItem, position: Int) {
        Log.d("innerRecycleView","内部数据")
        val nowDate = mutableList?.get(position)?.updateTime?.let { MonthUtil.getNowDate(it) }
        holder.projectCardViewInnerCardItemBinding.textView5.text = nowDate
        holder.projectCardViewInnerCardItemBinding.textView6.text = mutableList?.get(position)?.blogTitle

        Log.d("recycleView", mutableList?.get(position)!!.blogTitle)
    }

    override fun getItemCount(): Int {
        if (mutableList == null){
            return 0
        }
        return mutableList.size
    }
}
