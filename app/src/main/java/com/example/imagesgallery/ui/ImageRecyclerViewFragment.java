package com.example.imagesgallery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagesgallery.Parser.ImageParser;
import com.example.imagesgallery.R;
import com.example.imagesgallery.dto.ImageDto;
import com.example.imagesgallery.network.HttpAsyncTask;
import com.example.imagesgallery.network.HttpImageApi;
import com.example.imagesgallery.network.HttpResponseListener;
import com.example.imagesgallery.util.Util;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class ImageRecyclerViewFragment extends Fragment implements HttpResponseListener {

    private static final String TAG = "ImageRecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 5;

    protected ImageRecyclerView recyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private ProgressBarDialog progressBarDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (ImageRecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        recyclerView.setAdapter();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    private void loadData() {
        showProgress(true);

        // FIXME: 2017. 4. 23.
        HttpImageApi httpImageService = HttpImageApi.retrofit.create(HttpImageApi.class);
        final Call<ResponseBody> call = httpImageService.getImageList();
        new HttpAsyncTask(this).execute(call);
    }

    @Override
    public void onSuccess(InputStream response) {
        showProgress(false);

        ImageParser imageParser = Util.IMAGE_PARSER_MAP.get(Util.BASEURL);
        List<ImageDto> imageList = imageParser.parse(response, Util.BASEURL);
        recyclerView.setData(imageList);
    }

    private void showProgress(boolean isVisible) {
        if(isVisible) {
            progressBarDialog = new ProgressBarDialog(getActivity());
            progressBarDialog.show();
        } else {
            progressBarDialog.dismiss();
        }
    }
}
