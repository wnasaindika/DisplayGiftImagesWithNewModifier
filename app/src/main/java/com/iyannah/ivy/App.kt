package com.iyannah.ivy

import android.app.Application
import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class App : Application(), ImageLoaderFactory, MyImageLoader by MyImageLoaderImpl() {
    override fun newImageLoader(): ImageLoader {
        return getImageLoader(this)
    }
}

interface MyImageLoader {
    fun getImageLoader(context: Context): ImageLoader
}

class MyImageLoaderImpl : MyImageLoader {
    override fun getImageLoader(context: Context): ImageLoader {
        return ImageLoader(context).newBuilder().components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }

        }.build()
    }

}
