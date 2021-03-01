package activitytest.example.com.roomdemo.main.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.BindingAdapter;

public class ImageViewBindingAdapter {
    @BindingAdapter ( "image" )
    public static void setImage(ImageView imageView,CharSequence imageUrl){
            if (!TextUtils.isEmpty ( imageUrl )){
                Uri parse = Uri.parse ( (String) imageUrl );
                Picasso.with ( imageView.getContext () ).load ( parse).into ( imageView );
            }else {
                imageView.setBackgroundColor ( Color.TRANSPARENT );
            }
    }

    @BindingAdapter ( "image" )
    public static void setImage(ImageView imageView,int imageResource){
        if (imageResource == 0){
            imageView.setBackgroundColor ( Color.TRANSPARENT );
        }
        imageView.setImageResource ( imageResource );
    }



}
