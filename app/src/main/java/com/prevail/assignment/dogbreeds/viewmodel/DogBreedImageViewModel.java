package com.prevail.assignment.dogbreeds.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prevail.assignment.dogbreeds.models.AllImagesByBreed;

import java.util.List;

public class DogBreedImageViewModel extends ViewModel {

    private AllImagesByBreed imageUrl;
    private MutableLiveData<String> selected;
    private String breedName;

    public void init() {
        imageUrl = new AllImagesByBreed();
        selected = new MutableLiveData<>();
    }

    public void setBreedName(String name){
        this.breedName = name;
    }

    public String getBreedName(){
        return breedName;
    }

    public MutableLiveData<List<String>> getAllImages() {
        imageUrl.setBreedName(this.breedName);
        return imageUrl.getImages();
    }

    public MutableLiveData<Integer> getErrorData() {
        return imageUrl.getErrorCode();
    }

}


