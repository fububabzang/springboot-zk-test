package com.realme.common.result;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultData {

    private Integer pushCode;

    private String pushMessage;

    private String data;
}
