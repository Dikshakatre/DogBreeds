package com.prevail.assignment.dogbreeds.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.prevail.assignment.dogbreeds.network.RetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.prevail.assignment.dogbreeds.Utilities.ERROR_CODE;

public class AllImagesByBreed extends APIResponse {

    private String breedName;
    private List<String> imageUrls = new ArrayList<>();
    private MutableLiveData<List<String>> breedMutableLiveImages = new MutableLiveData<>();
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public MutableLiveData<List<String>> getImages() {
        Callback<DogBreedImages> allImagesCallback = new Callback<DogBreedImages>() {
            @Override
            public void onResponse(Call<DogBreedImages> call, Response<DogBreedImages> response) {
                errorCode.setValue(response.code());
                DogBreedImages body = response.body();
                if (body != null && body.imageUrls != null) {
                    imageUrls.addAll(body.getImageUrls());
                    breedMutableLiveImages.setValue(imageUrls);
                }
            }

            @Override
            public void onFailure(Call<DogBreedImages> call, Throwable t) {
                errorCode.setValue(ERROR_CODE);
                Log.e("API Error", "Failed to retrieve all images for breed: " + breedName+ "\n Error: \n"+t.getMessage(), t);
            }
        };

        RetrofitInstance.getApi().getAllImages(breedName).enqueue(allImagesCallback);
        return breedMutableLiveImages;
    }
}

