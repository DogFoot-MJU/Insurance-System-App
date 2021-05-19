package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;

import java.util.List;

@Dao
public interface MainDao {

    @Insert
    void insert(MainEntity data);

    @Update
    void update(MainEntity... DogFootEntity);

    @Delete
    void delete(MainEntity data);

    @Query("SELECT * FROM main_table")
    LiveData<List<MainEntity>> getData();
}
