package com.prevail.assignment.dogbreeds.bindings;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prevail.assignment.dogbreeds.R;

public class ViewBindings {

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(40);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @BindingAdapter("visibility")
    public static void bindTextViewVisibility(TextView textView, int visibilityStatus) {
        if(textView != null){
            textView.setVisibility(visibilityStatus);
        }
    }

    @BindingAdapter("imageUrl")
    public static void bindSetImageUrl(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {
            if (imageView.getTag(R.id.image_url) == null || !imageView.getTag(R.id.image_url).equals(imageUrl)) {
                imageView.setImageBitmap(null);
                imageView.setTag(R.id.image_url, imageUrl);
                Glide.with(imageView)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.mipmap.error)
                        .override(200,200)
                        .placeholder(R.mipmap.loading)
                        .thumbnail(0.5f)
                        .into(imageView);
            }
        } else {
            imageView.setTag(R.id.image_url, null);
            imageView.setImageBitmap(null);
        }
    }
}
