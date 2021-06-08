package com.dogfoot.insurancesystemapp.isApp.constants;

import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Constant {
    public static final String URL = "http://192.168.2.2:8080/";
    //public static final String URL = "http://112.146.144.170:8080/";
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
    public static final String CUSTOMER_CONSULTING__ACTIVITY_ID="viewId";

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
    public enum UwDueProcessType {
        WAIT, APPROVE, REJECT
    }

    @Getter
    @RequiredArgsConstructor
    public enum UserRoleType {
        ROLE_USER("유저"),
        ROLE_UW("언더라이터"),
        ROLE_INSURANCE_PLANNER("보험설계자"),
        ROLE_INSURANCE_SELLER("보험판매자"),
        ROLE_INSURANCE_COMPENSATION_PLANNER("보험보상기획자"),
        ROLE_INSURANCE_COMPENSATION_HANDLER("보험보상처리자"),
        ROLE_CONTRACT_MANAGER("계약관리자"),
        ROLE_FINANCIAL_SUPERVISORY_SERVICE("금융감독원"),
        ROLE_ADMIN("총관리자");

        private final String name;
    }

}
