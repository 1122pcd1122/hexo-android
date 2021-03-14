package activitytest.example.com.roomdemo.project.adapter


import activitytest.example.com.roomdemo.R
import activitytest.example.com.roomdemo.databinding.ProjectCardviewItemBinding
import activitytest.example.com.roomdemo.main.database.entity.Blog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val mutableMap: MutableMap<Int, MutableList<Blog>>, private val context: Context, private val mutableListOf: MutableList<Int>): RecyclerView.Adapter<LogAdapter.LogInnerView>() {

    class LogInnerView(val cardViewItemBinding: ProjectCardviewItemBinding):RecyclerView.ViewHolder(cardViewItemBinding.root){

        val recycleView:RecyclerView = cardViewItemBinding.recycleView
        init {
            recycleView.layoutManager  = LinearLayoutManager(cardViewItemBinding.root.context,LinearLayoutManager.VERTICAL,false)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogInnerView {
        val inflate = DataBindingUtil.bind<ProjectCardviewItemBinding>(LayoutInflater.from(context).inflate(R.layout.project_cardview_item,null))!!
        return LogInnerView(inflate)
    }

    override fun onBindViewHolder(holder: LogInnerView, position: Int) {
        val cardViewItemBinding = holder.cardViewItemBinding
        val year = mutableListOf[position]
        cardViewItemBinding.textView2.text = year.toString()

        if (cardViewItemBinding.recycleView.adapter == null){
            Log.d("recycleView","添加")
            holder.recycleView.adapter = InnerLogAdapter(mutableMap[year],context)
            Log.d("recycleView","添加完成"+mutableMap[position]?.get(0)?.blogTitle)
        }
    }

    override fun getItemCount(): Int {
        return mutableMap.size
    }
}

