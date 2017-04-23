package com.example.imagesgallery.network;

import com.example.imagesgallery.Util;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public interface HttpImageApi {
    @GET("/collections/archive/slim-aarons.aspx/")
    Call<ResponseBody> getImageList();

    @GET("/{imageName}")
    Call<ResponseBody> getImage(@Path("imageName") String imageName);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Util.GETTYBASEURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
}
