package com.example.imagesgallery.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class CompositionImageCache implements ImageCache {
    MemoryCache memoryCache;
    DiskCache diskCache;

    //public static final CompositionImageCache instance = new CompositionImageCache();

    private CompositionImageCache() {
        memoryCache = new MemoryCache();
        diskCache = new DiskCache();
    }

    /*
    public static CompositionImageCache getInstance() {
        return instance;
    }*/

    public static CompositionImageCache getInstance() {
        return CompositionImageCacheHolder.instance;
    }

    private static class CompositionImageCacheHolder {
        public static final CompositionImageCache instance = new CompositionImageCache();
    }

    public synchronized void init(Context context) {
        memoryCache.init();
        diskCache.init(context);
    }

    public Bitmap get(String imageUri) {
        Bitmap bitmap = null;

        if(memoryCache.isExistMemoryCache(imageUri)) {
            bitmap = memoryCache.getBitmapFromMemCache(imageUri);
            Log.e("ImageService", "memory");
        } else if(diskCache.isExistDiskCache(getKey(imageUri))) {
            bitmap = diskCache.getBitmapFromDiskCache(getKey(imageUri));
            Log.e("ImageService", "disk");
        }

        return bitmap;
    }

    public void set(String imageUri, Bitmap bitmap) {
        addMemoryCache(imageUri, bitmap);
        addDiskCache(imageUri, bitmap);
    }

    private void addMemoryCache(String imageUri, Bitmap bitmap) {
        memoryCache.addBitmapToMemoryCache(imageUri, bitmap);
    }

    private void addDiskCache(String imageUri, Bitmap bitmap) {
        diskCache.addBitmapToDiskCache(getKey(imageUri), bitmap);
    }

    private String getKey(String imageUri) {
        return String.valueOf(imageUri.hashCode());
    }
}
