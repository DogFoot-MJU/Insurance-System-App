package com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity;

import android.content.Intent;

import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntityTool;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.view.MainViewModelActivity;


import java.util.ArrayList;
import java.util.Map;

public abstract class DogFootViewModelActivity extends MainViewModelActivity {

    // Associate
        // Model
    protected DogFootEntity entity;
        protected Map<DogFootEntity.EDogFootData, String> dataset;
        protected ArrayList<String> searchWords;

    @Override
    public void onPause() {
        super.onPause();
        DogFootEntityTool.save(this.viewModelTool, this.entity);
    }
    @Override
    public void viewModelDataUpdated() {
        ArrayList<DogFootEntity> dogFootEntityArray = DogFootEntityTool.convertMainEntityArrayToDomainArray(this.viewModelTool.getMainEntities());
        if(dogFootEntityArray.size()==0){
            MainEntity mainEntity = new MainEntity();
            mainEntity.setEntityString(DogFootEntityTool.fromDogFootEntityToString(new DogFootEntity()));
            this.viewModelTool.getModel().insert(mainEntity);
        }else{
            this.entity = dogFootEntityArray.get(0);
            this.dataset = this.entity.getDataset();
            this.searchWords = this.entity.getSearchWords();

            this.associateView();
            this.initializeView();
            this.dogFootEntityUpdated();
        }
    }

    protected abstract void associateView();
    protected abstract void initializeView();
    public abstract void dogFootEntityUpdated();

    public void startActivity(Class<?> cls){ this.startActivity(new Intent(this, cls)); }
}
