package com.example.imagesgallery;

import android.app.Application;
import android.os.Build;

import com.example.imagesgallery.imageLoader.CompositionImageCache;

/**
 * Created by hyemi on 2017. 4. 24..
 */

public class ImagesGalleryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CompositionImageCache.getInstance().init(getApplicationContext());
    }
}
