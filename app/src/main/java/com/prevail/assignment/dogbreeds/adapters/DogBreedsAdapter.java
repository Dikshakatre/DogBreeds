package com.prevail.assignment.dogbreeds.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.prevail.assignment.dogbreeds.BR;
import com.prevail.assignment.dogbreeds.models.DogBreed;
import com.prevail.assignment.dogbreeds.viewmodel.DogBreedsViewModel;

import java.util.List;

public class DogBreedsAdapter extends RecyclerView.Adapter<DogBreedsAdapter.DogBreedViewHolder> {

    private int layoutId;
    private List<DogBreed> breeds;
    private DogBreedsViewModel viewModel;

    public DogBreedsAdapter(@LayoutRes int layoutId, DogBreedsViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private DogBreed getObjForPosition(int position) {
        return breeds.get(position);
    }

    private int getLayoutId() {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return breeds == null ? 0 : breeds.size();
    }

    public DogBreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new DogBreedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DogBreedViewHolder holder, int position) {
        DogBreed obj = getObjForPosition(position);
        holder.bind(obj, viewModel);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId();
    }

    /**
     * This method is called by DogBreedsViewModel
     * @param breeds is the list used for populating recyclerview in this adapter
     */
    public void setDogBreeds(List<DogBreed> breeds) {
        this.breeds = breeds;
    }

    static class DogBreedViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        DogBreedViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        /**
         * This method is called by DogBreedsViewModel
         * @param dogBreed holds current breed details
         */
        void bind(DogBreed dogBreed, DogBreedsViewModel viewModel) {
            binding.setVariable(BR.dogBreedModel, dogBreed);
            binding.setVariable(BR.viewModel, viewModel);
            dogBreed.fetchImages();
            binding.executePendingBindings();
        }
    }
}

