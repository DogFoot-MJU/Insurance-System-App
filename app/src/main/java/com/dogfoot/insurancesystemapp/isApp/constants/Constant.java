package com.dogfoot.insurancesystemapp.isApp.constants;

import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class Constant {
    public static final String URL = "http://192.168.2.2:8080/";
    //mainActivity
    public static final String MAIN_ACTIVITY_FINISH_DIALOG_TITLE = "정말 종료하시겠습니까?";
    public static final String MAIN_ACTIVITY_FINISH_DIALOG_MESSAGE = "확인을 누르시면 어플리케이션을 종료합니다.";
    private static Constant constant = null;
    @Getter
    private Map<DogFootEntity.EDogFootData, String> dataset;
    private Constant(){
        dataset = new HashMap<>();
    }
    public static Constant getInstance(){
        if(constant==null){
            constant = new Constant();
        }
        return constant;
    }
}
