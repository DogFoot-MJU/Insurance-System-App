package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.db;
import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.room.Database;
import androidx.room.Room;

import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.dao.MainDao;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;

@Database(entities = {MainEntity.class}, version = 2, exportSchema = false)
public  abstract class MainDatabase extends androidx.room.RoomDatabase {
    // Constant
    private static final int NUMBER_OF_THREADS = 4;
    private static volatile MainDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MainDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MainDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, "main_database")
                            .fallbackToDestructiveMigration() // 버전 바꾸면 리셋
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MainDao mainDao();
}
