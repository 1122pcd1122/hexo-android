package activitytest.example.com.log_module.project.adapter

import activitytest.example.com.log_module.R
import activitytest.example.com.log_module.databinding.LogSecondItemBinding
import activitytest.example.com.log_module.project.bean.Article
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SecondAdapter(private val list: List<Article>?): RecyclerView.Adapter<SecondAdapter.SecondInnerViewHolder>() {
    class SecondInnerViewHolder(val logSecondItemBinding: LogSecondItemBinding):RecyclerView.ViewHolder(logSecondItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondInnerViewHolder {
        val inflate = DataBindingUtil.inflate<LogSecondItemBinding>(LayoutInflater.from(parent.context), R.layout.log_second_item, parent, false)
        return SecondInnerViewHolder(inflate)
    }


    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: SecondInnerViewHolder, position: Int) {
        val logSecondItemBinding = holder.logSecondItemBinding
        val article = list?.get(position)

        logSecondItemBinding.date.text = article?.month+" "+ (article?.day?.get(0) ?: "")
        logSecondItemBinding.title.text = article?.title
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}