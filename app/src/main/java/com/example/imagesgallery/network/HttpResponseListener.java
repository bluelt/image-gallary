package com.example.imagesgallery.network;

import java.io.InputStream;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public interface HttpResponseListener {
    public void onSuccess(InputStream response);
}
