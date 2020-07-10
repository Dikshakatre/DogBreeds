package com.prevail.assignment.dogbreeds.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prevail.assignment.dogbreeds.R;
import com.prevail.assignment.dogbreeds.adapters.DogBreedsAdapter;
import com.prevail.assignment.dogbreeds.models.AllDogBreeds;
import com.prevail.assignment.dogbreeds.models.DogBreed;

import java.util.List;

public class DogBreedsViewModel extends ViewModel {

    private AllDogBreeds allDogBreeds;
    private DogBreedsAdapter adapter;
    private MutableLiveData<DogBreed> selected;

    public void init() {
        allDogBreeds = new AllDogBreeds();
        selected = new MutableLiveData<>();
        adapter = new DogBreedsAdapter(R.layout.view_dog_breed, this);
    }

    public void fetchList() {
        allDogBreeds.fetchList();
    }

    public MutableLiveData<List<DogBreed>> getBreeds() {
        return allDogBreeds.getBreeds();
    }

    public DogBreedsAdapter getAdapter() {
        return adapter;
    }

    public void setDogBreedsInAdapter(List<DogBreed> breeds) {
        this.adapter.setDogBreeds(breeds);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<DogBreed> getSelected() {
        return selected;
    }

    public MutableLiveData<Integer> getErrorData() {
        return allDogBreeds.getErrorCode();
    }

    public void onItemClick(DogBreed dogBreed) {
        selected.setValue(dogBreed);
    }

    public void cleanSelected() {
        selected = new MutableLiveData<>();
    }

    public AllDogBreeds getAllDogBreeds() {
        return allDogBreeds;
    }
}
