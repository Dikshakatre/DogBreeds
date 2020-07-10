package com.prevail.assignment.dogbreeds.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.prevail.assignment.dogbreeds.R;
import com.prevail.assignment.dogbreeds.databinding.ActivityDogBreedsBinding;
import com.prevail.assignment.dogbreeds.viewmodel.DogBreedsViewModel;
import static com.prevail.assignment.dogbreeds.Utilities.BREED_NAME;

/**
 * The recyclerview showcase list of Dog Breed which is fetched from API using Retrofit instance.
 * [DogBreedsViewModel] viewmodel is designed for this activity to handle required network call as well as set adapter.
 */
public class DogBreedsActivity extends AppCompatActivity {

    private DogBreedsViewModel viewModel;
    private ActivityDogBreedsBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setupBindings(savedInstanceState);

        setupListClick();
        setupErrorView();
    }
    /**
     * @param savedInstanceState if null it will reinitialize the viwemodel for this class.
     *  This method databinding with layout.
     */
    private void setupBindings(Bundle savedInstanceState) {
       activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_dog_breeds);
        viewModel = new ViewModelProvider(this).get(DogBreedsViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        activityBinding.tvRefreshAllBreads.setOnClickListener(view -> setupListUpdate());

        setupListUpdate();
    }

    /**
     * fetchList() of view model call API and fetch list of Dog breeds.
     */
    private void setupListUpdate() {
        viewModel.fetchList();
        viewModel.getBreeds().observe(this, dogBreeds -> viewModel.setDogBreedsInAdapter(dogBreeds));
    }

    /**
     * This method handles onclick event of row item of recyclerview.
     */
    private void setupListClick() {
        viewModel.getSelected().observe(this, dogBreed -> {
            if (dogBreed != null) {
                Intent intent = new Intent(DogBreedsActivity.this, DogBreedImagesActivity.class);
                intent.putExtra(BREED_NAME, dogBreed.getBreedName());
                viewModel.cleanSelected();
                startActivity(intent);
            }
        });
    }

    /**
     * This method will update UI in case of error in data fetch from API.
     */
    private void setupErrorView() {
        viewModel.getErrorData().observe(this, status -> {
            if (status != 200) {
                activityBinding.listOfBreeds.setVisibility(View.GONE);
                activityBinding.tvTryAgain.setVisibility(View.VISIBLE);
                activityBinding.ivLoading.setVisibility(View.GONE);
            }
            else{
                activityBinding.listOfBreeds.setVisibility(View.VISIBLE);
                activityBinding.tvTryAgain.setVisibility(View.GONE);
                activityBinding.ivLoading.setVisibility(View.GONE);
            }
        });
    }
}
