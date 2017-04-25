package com.example.imagesgallery.imageLoader;


import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by hyemi on 2017. 4. 24..
 */

public interface ImageCache {
    public Bitmap get(String imageUri);
    public void set(String imageUri, Bitmap bitmap);
}
