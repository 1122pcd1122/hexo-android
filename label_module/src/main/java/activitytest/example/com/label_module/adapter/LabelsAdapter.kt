package activitytest.example.com.label_module.adapter




import activitytest.example.com.label_module.R
import activitytest.example.com.label_module.databinding.ReTagsItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class LabelsAdapter(private val list: List<String>?,val click:(String) -> Unit): RecyclerView.Adapter<LabelsAdapter.InnerViewHolder>() {
    class InnerViewHolder(val reTagsItemBinding: ReTagsItemBinding):RecyclerView.ViewHolder(reTagsItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val inflate = DataBindingUtil.inflate<ReTagsItemBinding>(LayoutInflater.from(parent.context), R.layout.re_tags_item, parent, false)

        return InnerViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val reTagsItemBinding = holder.reTagsItemBinding
        val get = list?.get(position)
        reTagsItemBinding.tag.text = get

        if (position == 0){
            if (get != null) {
                click(get)
            }
        }
        reTagsItemBinding.tag.setOnClickListener {
            get?.let { it1 -> click(it1) }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}