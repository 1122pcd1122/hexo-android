package activitytest.example.com.log_module.project.adapter


import activitytest.example.com.log_module.R
import activitytest.example.com.log_module.databinding.LogFirstItemBinding
import activitytest.example.com.log_module.project.bean.ArticleByYears

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OneAdapter: PagingDataAdapter<ArticleByYears, OneAdapter.InnerViewHolder>(COMPARATOR) {

    class InnerViewHolder(val logFirstItemBinding: LogFirstItemBinding) : RecyclerView.ViewHolder(logFirstItemBinding.root)


    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ArticleByYears>() {
            override fun areItemsTheSame(oldItem: ArticleByYears, newItem: ArticleByYears): Boolean {
                return oldItem.year == newItem.year
            }

            override fun areContentsTheSame(oldItem: ArticleByYears, newItem: ArticleByYears): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val item = getItem(position)
        val logFirstItemBinding = holder.logFirstItemBinding
        logFirstItemBinding.year.text = item?.year.toString()
        logFirstItemBinding.twoRecycleView.layoutManager = LinearLayoutManager(logFirstItemBinding.root.context)
        logFirstItemBinding.twoRecycleView.adapter = SecondAdapter(item?.listArticle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val inflate = DataBindingUtil.inflate<LogFirstItemBinding>(LayoutInflater.from(parent.context), R.layout.log_first_item, parent, false)
        return InnerViewHolder(inflate)
    }
}