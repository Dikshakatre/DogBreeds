package com.prevail.assignment.dogbreeds.models;

import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.prevail.assignment.dogbreeds.BR;
import com.prevail.assignment.dogbreeds.network.RetrofitInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogBreed extends BaseObservable {
    private String breedName;
    private List<String> subBreeds;
    private String thumbnailUrl1 = null;
    private String thumbnailUrl2 = null;
    private String thumbnailUrl3 = null;
    private int visibility;

    public DogBreed(String breedName, List<String> subBreeds) {
        this.breedName = breedName;
        this.subBreeds = subBreeds;
    }

    public String getBreedName() {
        return breedName;
    }

    @Bindable
    public String getThumbnailUrl1() {
        return thumbnailUrl1;
    }
    @Bindable
    public String getThumbnailUrl2() {
        return thumbnailUrl2;
    }
    @Bindable
    public String getThumbnailUrl3() {
        return thumbnailUrl3;
    }
    @Bindable
    public int getVisibility() {
       return visibility;
    }

    public void fetchImages() {
        if (thumbnailUrl1 != null) {
            return;
        }
        Callback<DogBreedImages> callback = new Callback<DogBreedImages>() {
            @Override
            public void onResponse(Call<DogBreedImages> call, Response<DogBreedImages> response) {
                DogBreedImages body = response.body();
                if (body != null && body.imageUrls != null ) {
                    if(body.imageUrls.size() > 0) {
                        thumbnailUrl1 = body.imageUrls.get(0);
                        notifyPropertyChanged(BR.thumbnailUrl1);
                    }
                    if(body.imageUrls.size() > 1){
                        thumbnailUrl2 = body.imageUrls.get(1);
                        notifyPropertyChanged(BR.thumbnailUrl2);
                    }

                    if(body.imageUrls.size() > 2){
                        thumbnailUrl3 = body.imageUrls.get(2);
                        notifyPropertyChanged(BR.thumbnailUrl3);
                    }

                    if (body.imageUrls.size() < 3) {
                        visibility = View.GONE;
                    }
                    else visibility = View.VISIBLE;
                    notifyPropertyChanged(BR.visibility);

                }
            }

            @Override
            public void onFailure(Call<DogBreedImages> call, Throwable t) {
                Log.e("DogBreed", t.getMessage(), t);
            }
        };
        RetrofitInstance.getApi().getThreeImagesByBreed(this.breedName).enqueue(callback);
    }
}
