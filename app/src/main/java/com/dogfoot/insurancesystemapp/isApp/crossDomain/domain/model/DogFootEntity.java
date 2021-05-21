package com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DogFootEntity {

    // Static Value
    public enum EDogFootData {AUTHORIZATION,AUTO_LOGIN, EMAIL, PASSWORD, NAME,PHONE,ADDRESS,RESIDENT, TRUE, FALSE,ID}

    // Component
    private Map<EDogFootData, String> dataset;  // email, pw, authorization... 등 엥간한 것들을 여기 저장
    private ArrayList<String> searchWords; // 검색 기록 저장

    // Constructor
    public DogFootEntity() {
        this.dataset = new HashMap<>();
        this.dataset.put(EDogFootData.AUTO_LOGIN, EDogFootData.FALSE.name());
        this.searchWords = new ArrayList<>();

    }
}
