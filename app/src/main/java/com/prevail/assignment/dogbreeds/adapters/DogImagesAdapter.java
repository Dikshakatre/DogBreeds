package com.prevail.assignment.dogbreeds.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prevail.assignment.dogbreeds.R;
import com.prevail.assignment.dogbreeds.databinding.RowImageBinding;

import java.util.List;

public class DogImagesAdapter extends RecyclerView.Adapter<DogImagesAdapter.ImageViewHolder> {
    private List<String> imageUrlList;
    private Context context;

    public DogImagesAdapter(Context context, List<String> allImages ) {
        this.imageUrlList = allImages;
        this.context = context;
    }

    @NonNull
    @Override
    public DogImagesAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowImageBinding rowImageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_image, parent,false);
        return new ImageViewHolder(rowImageBinding);
    }
    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param holder gives layout
     * @param position give current item position in list
     */
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imageUrl = imageUrlList.get(position);
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.error)
                .override(200,200)
                .placeholder(R.mipmap.loading)
                .thumbnail(0.5f)
                .into(holder.rowImageBinding.ivLoadingImages);
    }


    @Override
    public int getItemCount() {
        return this.imageUrlList.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private RowImageBinding rowImageBinding;

        ImageViewHolder(@NonNull RowImageBinding rowImageBinding) {
            super(rowImageBinding.getRoot());
            this.rowImageBinding = rowImageBinding;
        }
    }
}
