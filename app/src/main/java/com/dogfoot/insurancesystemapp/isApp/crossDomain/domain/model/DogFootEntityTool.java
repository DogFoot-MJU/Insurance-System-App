package com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.viewModel.MainViewModelTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class DogFootEntityTool {

    public static DogFootEntity fromStringToDogFootEntity(String value) {
        Type listType = new TypeToken<DogFootEntity>() {}.getType();
        return new Gson().fromJson(value,listType);
    }
    public static String fromDogFootEntityToString(DogFootEntity type) {
        Gson gson = new Gson();
        String json = gson.toJson(type);
        return json;
    }
    public static ArrayList<DogFootEntity> convertMainEntityArrayToDomainArray(ArrayList<MainEntity> mainEntities) {
        ArrayList<DogFootEntity> dogFootEntityArray = new ArrayList<>();
        for(MainEntity mainEntity : mainEntities) dogFootEntityArray.add(DogFootEntityTool.fromStringToDogFootEntity(mainEntity.getEntityString()));
        return dogFootEntityArray;
    }
    public static void save(MainViewModelTool viewModelTool, DogFootEntity entity) {
        ArrayList<MainEntity> mainEntityArray = viewModelTool.getMainEntities();
        if(mainEntityArray.size()!=0){
            MainEntity mainEntity = mainEntityArray.get(0);
            mainEntity.setEntityString(DogFootEntityTool.fromDogFootEntityToString(entity));
            viewModelTool.getModel().update(mainEntity);
        }
    }
}
