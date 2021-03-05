package activitytest.example.com.roomdemo.main.adapter

import android.graphics.Color
import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageUrl: CharSequence?) {
        if (!TextUtils.isEmpty(imageUrl)) {
            val parse = Uri.parse(imageUrl as String?)
            Picasso.with(imageView.context).load(parse).into(imageView)
        } else {
            imageView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageResource: Int) {
        if (imageResource == 0) {
            imageView.setBackgroundColor(Color.TRANSPARENT)
        }
        imageView.setImageResource(imageResource)
    }
}