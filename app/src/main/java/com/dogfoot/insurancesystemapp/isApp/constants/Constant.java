package com.dogfoot.insurancesystemapp.isApp.constants;

import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Constant {
    public static final String URL = "http://112.146.144.170:8080/";
    public static final String LIST_FRAGMENT_FAILURE_DIALOG_TITLE="연결 실패";
    //mainActivity
    public static final String MAIN_ACTIVITY_FINISH_DIALOG_TITLE = "정말 종료하시겠습니까?";
    public static final String MAIN_ACTIVITY_FINISH_DIALOG_MESSAGE = "확인을 누르시면 어플리케이션을 종료합니다.";
    private static Constant constant = null;

    //logoutActivity
    public static final String LOG_OUT_ACTIVITY_SUCCESSFUL_DIALOG_TITLE = "성공적으로 로그아웃하였습니다.";
    public static final String LOG_OUT_ACTIVITY_SUCCESSFUL_DIALOG_MESSAGE = "성공적으로 로그아웃하였습니다.";
    public static final String LOG_OUT_ACTIVITY_FAILURE_DIALOG_TITLE = "로그인 되어 있지 않습니다.";
    public static final String LOG_OUT_ACTIVITY_FAILURE_DIALOG_MESSAGE = "로그인 되어 있지 않습니다.";

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

    @Getter
    @RequiredArgsConstructor
    public enum DevelopmentState {

        PLAN("기획"), DESIGN("설계"), AUTHORIZE("인가"), APPROVE("승인");

        private final String value;

    }

    public enum DriverLicence {
        TYPE_1_NORMAL, TYPE_2_NORMAL;
    }

    public enum SafetyRank {
        GREEN, RED, BLUE
    }
}
