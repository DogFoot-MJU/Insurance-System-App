package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Files {

    @SerializedName("filename")
    private String filename;

    @SerializedName("fileOriginName")
    private String fileOriginName;

    @SerializedName("fileUrl")
    private String fileUrl;


}
