package com.dogfoot.insurancesystemapp.isApp.jungwoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class InsurancePlanning {

    private int id;
    private String name;
    private String payment;
    private String state;
    private String type;
    
}
