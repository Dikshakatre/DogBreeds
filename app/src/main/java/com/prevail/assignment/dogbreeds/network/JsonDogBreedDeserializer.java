package com.prevail.assignment.dogbreeds.network;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.prevail.assignment.dogbreeds.models.AllDogBreeds;
import com.prevail.assignment.dogbreeds.models.DogBreed;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonDogBreedDeserializer implements JsonDeserializer<AllDogBreeds> {
    private Type listType = new TypeToken<List<String>>() {}.getType();

    @Override
    public AllDogBreeds deserialize(JsonElement response, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        AllDogBreeds breeds = new AllDogBreeds();
        Gson jsonTypeConverter = new Gson();
        if (response.isJsonObject()) {
            JsonElement message = response.getAsJsonObject().get("message");
            if (message!=null) {
                JsonObject jsonObject = message.getAsJsonObject();
                for (Map.Entry<String, JsonElement> breedEntry : jsonObject.entrySet()) {
                    List<String> subBreeds = jsonTypeConverter.fromJson(breedEntry.getValue(),listType);
                    DogBreed db = new DogBreed(breedEntry.getKey(), subBreeds);
                    breeds.addBreed(db);
                }
            }
        }
        return breeds;
    }
}
