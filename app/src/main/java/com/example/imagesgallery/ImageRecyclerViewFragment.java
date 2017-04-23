package com.example.imagesgallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imagesgallery.Parser.ImageParser;
import com.example.imagesgallery.dto.ImageDto;
import com.example.imagesgallery.network.HttpAsyncTask;
import com.example.imagesgallery.network.HttpImageApi;
import com.example.imagesgallery.network.HttpResponseListener;
import com.example.imagesgallery.ui.ImagesRecyclerView;

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
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected ImageRecyclerViewFragment.LayoutManagerType mCurrentLayoutManagerType;

    protected ImagesRecyclerView recyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_recyclerview, container, false);
        rootView.setTag(TAG);

        recyclerView = (ImagesRecyclerView) rootView.findViewById(R.id.recyclerView);
        mCurrentLayoutManagerType = ImageRecyclerViewFragment.LayoutManagerType.GRID_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            mCurrentLayoutManagerType = (ImageRecyclerViewFragment.LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        recyclerView.setAdapter();
        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(ImageRecyclerViewFragment.LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = ImageRecyclerViewFragment.LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = ImageRecyclerViewFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = ImageRecyclerViewFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void loadData() {
        // FIXME: 2017. 4. 23.
        HttpImageApi httpImageService = HttpImageApi.retrofit.create(HttpImageApi.class);
        final Call<ResponseBody> call = httpImageService.getImageList();
        new HttpAsyncTask(this).execute(call);
    }

    @Override
    public void onSuccess(InputStream response) {
        ImageParser imageParser = Util.IMAGE_PARSER_MAP.get(Util.BASEURL);
        List<ImageDto> imageList = imageParser.parse(response, Util.BASEURL);
        recyclerView.setData(imageList);
    }

}
