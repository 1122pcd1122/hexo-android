package activitytest.example.com.roomdemo.main.adapter

import activitytest.example.com.roomdemo.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerviewAdapter(private val context: Context, private val data: List<String>, private val textViewClickListener: TextViewClickListener) : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position]
        holder.name.setOnClickListener { v -> textViewClickListener.clickListener(v) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item)

    }

    interface TextViewClickListener {
        fun clickListener(textView: View)
    }
}