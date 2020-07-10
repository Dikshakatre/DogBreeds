package com.prevail.assignment.dogbreeds.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.prevail.assignment.dogbreeds.R;
import com.prevail.assignment.dogbreeds.adapters.DogBreedsAdapter;
import com.prevail.assignment.dogbreeds.models.AllDogBreeds;
import com.prevail.assignment.dogbreeds.models.DogBreed;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

public class DogBreedsViewModelTest {
    @Mock
    DogBreedsViewModel viewModel;

    @Mock
    DogBreedsAdapter adapter;

    @Mock
    AllDogBreeds allDogBreeds;

    private RecyclerView recyclerView;

    private List<DogBreed> dogBreedList;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new DogBreedsViewModel();
        viewModel.init();
        viewModel.fetchList();
        recyclerView = Mockito.mock(RecyclerView.class);
    }

    @Test
    public void fetchBreedList_notNull_Test() {
        dogBreedList = viewModel.getAllDogBreeds().getBreedList();
        assertNotNull(dogBreedList);
    }

    @Test
    public void getAdapter_null_Test() {
        assertNotNull(viewModel.getAdapter());
    }

    @Test
    public void getAdapter_notNull_Test() {
        dogBreedList = viewModel.getAllDogBreeds().getBreedList();
        adapter = new DogBreedsAdapter(R.layout.view_dog_breed, viewModel);
        adapter.setDogBreeds(dogBreedList);
        recyclerView.setAdapter(adapter);

        assertNotNull(viewModel.getAdapter());
    }

    @Test
    public void getSelected_notNull_Test() {
        dogBreedList = viewModel.getAllDogBreeds().getBreedList();
        MutableLiveData<DogBreed> data = viewModel.getSelected();
        assertNotNull(data);
    }

}