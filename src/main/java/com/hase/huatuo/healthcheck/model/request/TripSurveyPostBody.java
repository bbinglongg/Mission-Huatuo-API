package com.hase.huatuo.healthcheck.model.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripSurveyPostBody {

    private String staffId;
    private String mobileNumber;
    private String location;
    private String temperature;
    private String aloneOrNot;
    private String familyContact;
    private String familyCondition;
    private String transitCity;
    private String transitMethod;
    private String transitNo;
    private String transitDate;
    private String transitWuHan;
    private String transitHuBei;
    private String transitCityOfHuBei;
    private String contactHistory;
    private String bodyHistory;
}
