package com.prevail.assignment.dogbreeds.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.prevail.assignment.dogbreeds.R;
import com.prevail.assignment.dogbreeds.adapters.DogImagesAdapter;
import com.prevail.assignment.dogbreeds.databinding.ActivityBreedGalleryBinding;
import com.prevail.assignment.dogbreeds.viewmodel.DogBreedImageViewModel;
import java.util.List;

import static com.prevail.assignment.dogbreeds.Utilities.BREED_NAME;

/**
 * This activity show case all images of a breed.
 * [breedName] received as intent extra from DogBreeds activity.
 *
 * [DogBreedImageViewModel] viewmodel is designed for this activity to handle required network call as well as setting
 *  adapter for Images on recyclerview.
 */
public class DogBreedImagesActivity extends AppCompatActivity {

    private DogBreedImageViewModel dogBreedImageViewModel;
    private List<String> imageUrlList;
    private ActivityBreedGalleryBinding activityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String breedName = intent.getStringExtra(BREED_NAME);
        setupBindings(savedInstanceState,breedName);
    }

    private void setupBindings(Bundle savedInstanceState, String breedName) {
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_breed_gallery);
        dogBreedImageViewModel = new ViewModelProvider(this).get(DogBreedImageViewModel.class);
        if (savedInstanceState == null) {
            dogBreedImageViewModel.init();
        }
        activityBinding.tvToolBarDogBreedTitle.setText(breedName);
        activityBinding.tvRefreshAllImages.setOnClickListener(view -> setupListUpdate());
        dogBreedImageViewModel.setBreedName(breedName);
        activityBinding.setModel(dogBreedImageViewModel);
        setupListUpdate();
        setupErrorView();
    }

    /**
     * getAllImages() method fetch the list of images and returns MutableLiveData
     */
    private void setupListUpdate() {
        dogBreedImageViewModel.getAllImages().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> dogBreedsImageUrl) {
                imageUrlList = dogBreedsImageUrl;
                if(imageUrlList.size() != 0){
                    showImagesRecyclerView();
                }
            }
        });
    }

    /**
     * DogImagesAdapter is set for recyclerview.
     */
    private void showImagesRecyclerView() {
        DogImagesAdapter dogImagesAdapter = new DogImagesAdapter(DogBreedImagesActivity.this, imageUrlList);
       if (DogBreedImagesActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            activityBinding.rvAllImages.setLayoutManager(new GridLayoutManager(DogBreedImagesActivity.this, 2));
        } else {
            activityBinding.rvAllImages.setLayoutManager(new GridLayoutManager(DogBreedImagesActivity.this, 4));
        }
        activityBinding.rvAllImages.setItemAnimator(new DefaultItemAnimator());
        activityBinding.rvAllImages.setAdapter(dogImagesAdapter);
        dogImagesAdapter.notifyDataSetChanged();
        activityBinding.rvAllImages.setHasFixedSize(true);
        activityBinding.rvAllImages.setItemViewCacheSize(40);
    }

    /**
     * This method will update UI in case of error in data fetch from API.
     */
    private void setupErrorView() {
        dogBreedImageViewModel.getErrorData().observe(this, error -> {
            if (error != 200) {
                activityBinding.rvAllImages.setVisibility(View.GONE);
                activityBinding.tvTryAgain.setVisibility(View.VISIBLE);
                activityBinding.ivLoadingImages.setVisibility(View.GONE);
            }
            else{
                activityBinding.rvAllImages.setVisibility(View.VISIBLE);
                activityBinding.tvTryAgain.setVisibility(View.GONE);
                activityBinding.ivLoadingImages.setVisibility(View.GONE);
            }
        });
    }
}