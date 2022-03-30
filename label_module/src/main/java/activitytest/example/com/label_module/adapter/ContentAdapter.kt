package activitytest.example.com.label_module.adapter



import activitytest.example.com.label_module.Article
import activitytest.example.com.label_module.R
import activitytest.example.com.label_module.databinding.ReTagscontentItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class ContentAdapter(private val list: List<Article>):RecyclerView.Adapter<ContentAdapter.InnerViewHolder>() {
    class InnerViewHolder(val reTagsContentItemBinding: ReTagscontentItemBinding):RecyclerView.ViewHolder(reTagsContentItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val inflate = DataBindingUtil.inflate<ReTagscontentItemBinding>(LayoutInflater.from(parent.context), R.layout.re_tagscontent_item, parent, false)
        return InnerViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val reTagsContentItemBinding = holder.reTagsContentItemBinding
        val get = list[position]
        reTagsContentItemBinding.blogTitle.text = get.title
//        reTagsContentItemBinding.blogContent.text = get.date
//        reTagsContentItemBinding.blogTags.text = get.tags
    }


    override fun getItemCount(): Int {
        return list.size
    }
}