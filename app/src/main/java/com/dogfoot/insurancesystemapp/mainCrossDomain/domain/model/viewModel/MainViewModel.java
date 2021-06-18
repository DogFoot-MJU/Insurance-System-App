package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.repository.MainRepository;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    // Associate
    private LiveData<List<MainEntity>> dataset;

    // Component
    private MainRepository mRepository;

    // Constructor
    public MainViewModel(Application application) {
        super(application);

        // Create Component
        this.mRepository = new MainRepository(application);

        // Associate Model
        this.dataset = this.mRepository.getDataset();
    }

    // Method
    public void insert(MainEntity data) { this.mRepository.insert(data); }
    public void update(MainEntity... dataset) { this.mRepository.update(dataset); }
    public void delete(MainEntity data) { this.mRepository.delete(data); }
    // Getter & Setter
    public LiveData<List<MainEntity>> getDataset() { return dataset; }
}
