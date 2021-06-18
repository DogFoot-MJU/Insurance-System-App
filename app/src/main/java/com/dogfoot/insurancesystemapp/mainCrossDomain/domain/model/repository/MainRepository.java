package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.dao.MainDao;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.db.MainDatabase;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;

import java.util.List;


public class MainRepository {
    // Association
    private MainDao mainDao;
    private LiveData<List<MainEntity>> dataset;

    // Constructor
    public MainRepository(Application application) {
        MainDatabase db = MainDatabase.getDatabase(application);
        this.mainDao = db.mainDao();
        this.dataset = this.mainDao.getData();
    }

    public void insert(MainEntity data) { MainDatabase.databaseWriteExecutor.execute(() -> { this.mainDao.insert(data); }); }
    public void update(MainEntity... dataset) { MainDatabase.databaseWriteExecutor.execute(() -> { this.mainDao.update(dataset); }); }
    public void delete(MainEntity data) { MainDatabase.databaseWriteExecutor.execute(() -> { this.mainDao.delete(data); }); }
    public LiveData<List<MainEntity>> getDataset() { return this.dataset; }

}
