package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel;

import android.app.Activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;

import java.util.ArrayList;
import java.util.List;

public class MainViewModelTool implements Observer {
    // Associate
        // View
        private final Activity activity;
        // Model
        protected MainViewModel model;
        private final ViewModelToolCallback callback;
        private final ArrayList<MainEntity> mainEntities;

    // Constructor
    public MainViewModelTool(Activity activity, ViewModelStoreOwner viewModelStoreOwner, ViewModelToolCallback callback) {
        // Create Component
        this.mainEntities = new ArrayList<>();

        // Associate View
        this.activity=activity;

        // Associate Model
        this.model = new ViewModelProvider(viewModelStoreOwner,
                ViewModelProvider.AndroidViewModelFactory.getInstance(activity.getApplication())).get(MainViewModel.class);
        this.callback=callback;
    }
    public void startObserve(){ this.model.getDataset().observe((LifecycleOwner) this.activity, this); }
    public void stopObserve(){ this.model.getDataset().removeObservers((LifecycleOwner) this.activity); }

    @Override
    public void onChanged(Object o) {
        this.updateMainEntities();
        this.callback.viewModelDataUpdated();
    }
    private void updateMainEntities(){
        this.mainEntities.clear();
        List<MainEntity> mainEntities = this.model.getDataset().getValue();
        for(MainEntity mainEntity : mainEntities)this.mainEntities.add(mainEntity);
    }

    public ArrayList<MainEntity> getMainEntities(){ return this.mainEntities; }
    public MainViewModel getModel() { return model; }
}
