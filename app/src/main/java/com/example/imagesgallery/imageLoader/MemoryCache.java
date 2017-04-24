package com.example.imagesgallery.imageLoader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by hyemi on 2017. 4. 24..
 */

public class MemoryCache {
    private LruCache<String, Bitmap> mMemoryCache;

    public void init() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public boolean isExistMemoryCache(String key) {
        boolean result = (getBitmapFromMemCache(key) == null)? false : true;
        return result;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
