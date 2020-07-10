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

public class AllDogBreeds extends APIResponse {
    private List<DogBreed> breedList = new ArrayList<>();

    private MutableLiveData<List<DogBreed>> breeds = new MutableLiveData<>();

    public void addBreed(DogBreed db) {
        breedList.add(db);
    }

    public MutableLiveData<List<DogBreed>> getBreeds() {
        return breeds;
    }

    public void fetchList() {
        Callback<AllDogBreeds> callback = new Callback<AllDogBreeds>() {
            @Override
            public void onResponse(Call<AllDogBreeds> call, Response<AllDogBreeds> response) {
                errorCode.setValue(response.code());
                AllDogBreeds body = response.body();
                if(body!=null) {
                    breeds.setValue(body.breedList);
                }
            }

            @Override
            public void onFailure(Call<AllDogBreeds> call, Throwable t) {
                errorCode.setValue(ERROR_CODE);
                Log.e("AllDogBreeds", t.getMessage(), t);
            }
        };

        RetrofitInstance.getApi().getBreeds().enqueue(callback);
    }

    public List<DogBreed> getBreedList() {
        return breedList;
    }
}
