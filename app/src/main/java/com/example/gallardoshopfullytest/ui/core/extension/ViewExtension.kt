package com.example.gallardoshopfullytest.ui.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

private const val IMAGE_URL_PREFIX = "https://it-it-media.shopfully.cloud/images/volantini/"
private const val IMAGE_URL_POSTFIX = "@3x.jpg"

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadFlyerImage(url: String) {
    val flyerImageUrl = IMAGE_URL_PREFIX + url + IMAGE_URL_POSTFIX
    this.load(flyerImageUrl)
}
