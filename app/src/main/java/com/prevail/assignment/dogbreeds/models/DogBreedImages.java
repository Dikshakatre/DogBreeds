package com.prevail.assignment.dogbreeds.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DogBreedImages extends APIResponse {
    @SerializedName("message")
    List<String> imageUrls;

    List<String> getImageUrls() {
        return imageUrls;
    }
}
