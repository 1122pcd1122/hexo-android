

package activitytest.example.com.roomdemo.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import activitytest.example.com.roomdemo.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {


    private final Context context;
    private final List<String> data;
    private final TextViewClickListener textViewClickListener;
    public RecyclerviewAdapter(Context context,List<String> data,TextViewClickListener textViewClickListener){
        this.context = context;
        this.data = data;
        this.textViewClickListener = textViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.menu_item_card,parent,false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(data.get(position));
        holder.name.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                textViewClickListener.clickListener ( v );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item);

        }
    }


    public interface TextViewClickListener{
        void clickListener(View textView);
    }
}
