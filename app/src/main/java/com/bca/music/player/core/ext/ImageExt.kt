package com.bca.music.player.core.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bca.music.player.R
import com.bca.music.player.core.di.GlideApp


fun ImageView.loadImage(
    path: String?,
    @DrawableRes errorImage: Int = R.drawable.ic_img_placeholder,
    @DrawableRes placeholder: Int = R.drawable.ic_img_placeholder
) {
    GlideApp.with(this)
        .load(path)
        .placeholder(placeholder)
        .error(errorImage)
        .into(this)
}



