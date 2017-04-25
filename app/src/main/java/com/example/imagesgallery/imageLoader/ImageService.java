package com.example.imagesgallery.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.imagesgallery.network.HttpAsyncTask;
import com.example.imagesgallery.network.HttpImageApi;
import com.example.imagesgallery.network.HttpResponseListener;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by hyemi on 2017. 4. 24..
 */

public class ImageService {
    Context context;
    ImageCacheListener listener;

    public ImageService(Context context, ImageCacheListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void loadBitmap(String src) {
        if(listener == null) {
            return;
        }
        // FIXME: 2017. 4. 24. ImageService는 여러개이나 CompositionImageCache는 하나
        Bitmap bitmap = CompositionImageCache.getInstance().get(src);

        if(bitmap != null) {
            Log.e("ImageService", "cache");
            listener.loadBitmap(bitmap);
        } else {
            Log.e("ImageService", "download");
            getBitmapFromUrl(src);
        }
    }

    public void getBitmapFromUrl(final String src) {
        // FIXME: 2017. 4. 23. //new callback return bitmap //loader구현
        HttpImageApi ImageApi = HttpImageApi.retrofit.create(HttpImageApi.class);

        final Call<ResponseBody> call = ImageApi.getImage(src);
        new HttpAsyncTask(new HttpResponseListener() {
            @Override
            public void onSuccess(InputStream response) {
                Bitmap bitmap = BitmapFactory.decodeStream(response);
                CompositionImageCache.getInstance().set(src, bitmap);
                listener.loadBitmap(bitmap);
            }
        }).execute(call);
    }
}
