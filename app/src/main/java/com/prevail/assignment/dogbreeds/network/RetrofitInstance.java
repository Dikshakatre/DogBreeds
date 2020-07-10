package com.prevail.assignment.dogbreeds.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prevail.assignment.dogbreeds.models.AllDogBreeds;
import com.prevail.assignment.dogbreeds.models.DogBreedImages;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static ApiInterface api;
    private static Gson gsonConverter;
    private static final String BASE_URL = "https://dog.ceo";

    public static ApiInterface getApi() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            gsonConverter = new GsonBuilder()
                    .registerTypeAdapter(
                            AllDogBreeds.class,
                            new JsonDogBreedDeserializer())
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {
        @GET("/api/breeds/list/all")
        Call<AllDogBreeds> getBreeds();

        @GET("/api/breed/{breed}/images/random/3")
        Call<DogBreedImages> getThreeImagesByBreed(@Path("breed") String breed);

        @GET("/api/breed/{breed}/images")
        Call<DogBreedImages> getAllImages(@Path("breed") String breed);
    }
}