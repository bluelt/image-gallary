package com.example.imagesgallery.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.imagesgallery.dto.ImageDto;

import java.util.List;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class ImagesRecyclerView extends RecyclerView {
    private Context context;
    private ImageRecyclerAdapter mAdapter;


    public ImagesRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    public ImagesRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public ImagesRecyclerView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        mAdapter = new ImageRecyclerAdapter(context);
        setAdapter();
    }

    public void setData(List<ImageDto> dtoList) {
        mAdapter.setData(dtoList);
    }

    public void setAdapter() {
        setAdapter(mAdapter);
    }
}
