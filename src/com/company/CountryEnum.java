package com.company;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author heyhuo
 * @create 2021-03-12
 */
public enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");


    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountryEnum(Integer retCode, String retMessag) {
        this.retCode = retCode;
        this.retMessage = retMessag;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] myArr = CountryEnum.values();
        for (CountryEnum countryEnum : myArr) {
            if (index == countryEnum.getRetCode()) {
                return countryEnum;
            }
        }
        return null;
    }
}
