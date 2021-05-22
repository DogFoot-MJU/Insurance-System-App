package com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;


import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntityTool;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.view.MainViewModelFragment;

import java.util.ArrayList;
import java.util.Map;

public abstract class DogFootViewModelFragment extends MainViewModelFragment {

    // Associate
        // Model
        protected DogFootEntity entity;
        protected static Map<DogFootEntity.EDogFootData, String> dataset;
        protected ArrayList<String> searchWords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.getLayoutId(), container, false);
    }
    @Override
    public void onPause() {
        super.onPause();
        DogFootEntityTool.save(this.viewModelTool, this.entity);
    }
    public void save(){
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

            this.associateView(this.getView());
            this.initializeView();
            this.dogFootEntityUpdated();
        }
    }

    protected abstract int getLayoutId();
    protected abstract void associateView(View view);
    protected abstract void initializeView();
    protected abstract void dogFootEntityUpdated();

    public void startActivity(Class<?> cls){ this.startActivity(new Intent(this.getContext(), cls)); }
    protected void navigateTo(int id){ Navigation.findNavController(this.getView()).navigate(id); }
    protected String getStringOfId(int id){ return this.getResources().getString(id); }
}
