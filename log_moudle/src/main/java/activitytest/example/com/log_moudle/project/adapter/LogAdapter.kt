package activitytest.example.com.log_moudle.project.adapter


import activitytest.example.com.log_moudle.R
import activitytest.example.com.log_moudle.databinding.LogCardviewItemBinding
import activitytest.example.com.log_moudle.project.bean.ArticlesByYear
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val context: Context, private val articlesByYear: ArticlesByYear): RecyclerView.Adapter<LogAdapter.LogInnerView>() {

    private val articleNameList = articlesByYear.articleNameList

    class LogInnerView(val cardViewItemBinding: LogCardviewItemBinding):RecyclerView.ViewHolder(cardViewItemBinding.root){

        val recycleView:RecyclerView = cardViewItemBinding.recycleView
        init {
            recycleView.layoutManager  = LinearLayoutManager(cardViewItemBinding.root.context,LinearLayoutManager.VERTICAL,false)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogInnerView {
        val inflate = DataBindingUtil.bind<LogCardviewItemBinding>(LayoutInflater.from(context).inflate(R.layout.log_cardview_item,null))!!
        return LogInnerView(inflate)
    }

    override fun onBindViewHolder(holder: LogInnerView, position: Int) {
        val cardViewItemBinding = holder.cardViewItemBinding

        val list = articleNameList[position]
        cardViewItemBinding.textView2.text = list.year.toString()
        if (cardViewItemBinding.recycleView.adapter == null){
            Log.d("recycleView","添加")
            cardViewItemBinding.recycleView.adapter = InnerLogAdapter(list.articles,context)
            Log.d("recycleView","添加完成"+list.year)
        }
    }

    override fun getItemCount(): Int {
        return articleNameList.size
    }
}

