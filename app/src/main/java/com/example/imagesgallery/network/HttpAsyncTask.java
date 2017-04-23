package com.example.imagesgallery.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class HttpAsyncTask extends AsyncTask<Call, Void, Response<ResponseBody>> {
    HttpResponseListener listener;

    public HttpAsyncTask(HttpResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected Response<ResponseBody> doInBackground(Call... params) {
        try {
            Call<ResponseBody> call = params[0];
            Response<ResponseBody> response = call.execute();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            //to-Do network Error 처리
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<ResponseBody> response) {
        if (response == null && !response.isSuccessful()) {
            return;
        }

        Log.e("**onSuccess**", response.body().source().toString());
        InputStream stream = response.body().byteStream();
        if(listener != null) {
            listener.onSuccess(stream);
        }

    }
}


