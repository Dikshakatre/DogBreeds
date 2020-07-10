package com.prevail.assignment.dogbreeds.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.prevail.assignment.dogbreeds.models.AllImagesByBreed;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

public class DogBreedImageViewModelTest {

    @Mock
    DogBreedImageViewModel viewModel;

    @Mock
    AllImagesByBreed allImagesByBreed;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new DogBreedImageViewModel();
        viewModel.init();
    }

    @Test
    public void getBreedName_null_Test() {
        assertNull(viewModel.getBreedName());
    }

    @Test
    public void getBreedName_notNull_Test() {
        viewModel.setBreedName("african");
        assertNotNull(viewModel.getBreedName());
    }

    @Test
    public void getAllImages_null_Test() {
        MutableLiveData<List<String>> data = viewModel.getAllImages();
        assertNull(data.getValue());
    }

    @Test
    public void getAllImages_notNull_Test() {
        viewModel.setBreedName("african");
        MutableLiveData<List<String>> data = viewModel.getAllImages();
        assertNotNull(data);
    }

}