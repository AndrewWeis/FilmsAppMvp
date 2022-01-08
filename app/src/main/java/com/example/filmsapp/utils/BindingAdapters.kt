package com.example.filmsapp.utils

import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmsapp.R

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl == null) {
        imgView.setImageDrawable(ContextCompat.getDrawable(imgView.context,
            R.drawable.image_not_found
        ))
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

/**
 * Highlights the selected genre in color
 */
@BindingAdapter("selectGenre")
fun selectGenre(btn: Button, isSelected: Boolean) {
    if (isSelected) {
        btn.setBackgroundColor(ContextCompat.getColor(btn.context, R.color.teal_700))
    } else {
        btn.setBackgroundColor(ContextCompat.getColor(btn.context, R.color.primaryBlack))
    }
}