package com.example.imagesgallery.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.imagesgallery.R;
import com.example.imagesgallery.dto.ImageDto;
import com.example.imagesgallery.network.HttpAsyncTask;
import com.example.imagesgallery.network.HttpImageApi;
import com.example.imagesgallery.network.HttpResponseListener;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private List<ImageDto> dtoList = null;

    public ImageRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ImageDto> dtoList) {
        this.dtoList = dtoList;

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_item, parent, false);

        return new ImageRecyclerAdapter.ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ImageDto item = dtoList.get(position);
        if(item == null) {
            return;
        }

        // FIXME: 2017. 4. 23. //new callback return bitmap //loader구현
        HttpImageApi ImageApi = HttpImageApi.retrofit.create(HttpImageApi.class);

        final Call<ResponseBody> call = ImageApi.getImage(item.getSrc());
        new HttpAsyncTask(new HttpResponseListener() {
            @Override
            public void onSuccess(InputStream response) {
                Bitmap bitmap = BitmapFactory.decodeStream(response);
                ((ImageRecyclerAdapter.ImageHolder)holder).imageView.setImageBitmap(bitmap);
            }
        }).execute(call);
        //String url = item.getHost() + item.getSrc();
        //Glide.with(context).load(url).into(((GettyImageHolder)holder).imageView);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return dtoList == null ? 0 : dtoList.size();
    }

    public String getItemUrl(int position) {
        return dtoList.get(position).getLink();
    }

    private class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.getty_image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("RecyclerView", "***gogogo*** " + getItemUrl(getAdapterPosition()));
                }
            });
        }
    }
}
