package com.bca.music.player.core.ext

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.bca.music.player.R
import com.bca.music.player.core.di.GlideApp
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

fun TextView.loadImage(
    path: String,
    @DrawableRes errorImage: Int = R.drawable.ic_img_placeholder,
    width: Int = 200,
    height: Int = 200
) {
    GlideApp
        .with(this)
        .load(path)
        .placeholder(R.drawable.ic_img_placeholder)
        .error(errorImage)
        .apply(RequestOptions().fitCenter())
        .into(
            object : CustomTarget<Drawable>(width, height) {
                override fun onLoadCleared(placeholder: Drawable?) {
                    this@loadImage.setCompoundDrawablesWithIntrinsicBounds(
                        placeholder,
                        null,
                        null,
                        null
                    )
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    this@loadImage.setCompoundDrawablesWithIntrinsicBounds(
                        errorDrawable,
                        null,
                        null,
                        null
                    )
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    this@loadImage.setCompoundDrawablesWithIntrinsicBounds(
                        resource,
                        null,
                        null,
                        null
                    )
                }
            }
        )
}

