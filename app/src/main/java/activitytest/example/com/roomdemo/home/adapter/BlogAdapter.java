package activitytest.example.com.roomdemo.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import activitytest.example.com.roomdemo.R;
import activitytest.example.com.roomdemo.databinding.BlogItemCardBinding;
import activitytest.example.com.roomdemo.main.activity.WebViewActivity;
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogInnerView> {

    private final Context context;
    private final List<BlogIntroduce> blogIntroduceList;

    public BlogAdapter(Context context,List<BlogIntroduce> blogIntroduceList) {
        this.context = context;
        this.blogIntroduceList = blogIntroduceList;
    }

    @NonNull
    @Override
    public BlogInnerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BlogItemCardBinding viewDataBinding = DataBindingUtil.inflate ( LayoutInflater.from ( parent.getContext () ), R.layout.blog_item_card, parent, false );
        return new BlogInnerView ( viewDataBinding );

    }

    @Override
    public void onBindViewHolder(@NonNull BlogInnerView holder, int position) {

        holder.blogItemCardBinding.blogTags.setText ( blogIntroduceList.get ( position ).getTag () );
        holder.blogItemCardBinding.blogTitle.setText ( blogIntroduceList.get ( position ).getBlogTitle () );
        holder.blogItemCardBinding.blogContent.setText ( blogIntroduceList.get ( position ).getTime () );

        holder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, WebViewActivity.class );

                intent.putExtra ( "html",blogIntroduceList.get ( position ).getHtml () );

                context.startActivity ( intent );
            }
        } );
    }



    @Override
    public int getItemCount() {
        return blogIntroduceList.size ();
    }

    public static class BlogInnerView extends RecyclerView.ViewHolder{

        BlogItemCardBinding blogItemCardBinding;

        public BlogInnerView(@NonNull BlogItemCardBinding itemView) {
            super ( itemView.getRoot () );
            blogItemCardBinding = itemView;

        }
    }


}
