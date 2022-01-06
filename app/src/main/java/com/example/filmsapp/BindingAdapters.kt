package com.example.filmsapp

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl == null) {
        imgView.setImageDrawable(ContextCompat.getDrawable(imgView.context, R.drawable.image_not_found))
    } else {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.image_not_found))
            .into(imgView)
    }
}