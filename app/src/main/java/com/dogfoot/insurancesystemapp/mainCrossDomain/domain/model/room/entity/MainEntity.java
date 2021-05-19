package com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity;
import lombok.Getter;
import lombok.Setter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Getter
@Setter
@Entity(tableName = "main_table")
public class MainEntity {
    // Attributes
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "entityString")
    private String entityString;
}
