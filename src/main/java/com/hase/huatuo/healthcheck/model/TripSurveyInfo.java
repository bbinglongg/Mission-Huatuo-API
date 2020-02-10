package com.hase.huatuo.healthcheck.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name="trip_survey_hacn")
public class TripSurveyInfo {

    @Id
    @Column(name = "serail_number")
    private String serailNumber;
    @Column(name = "staff_id")
    private String staffId;
    @Column(name = "staff_name")
    private String staffName;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "location")
    private String location;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "alone_or_not")
    private String aloneOrNot;
    @Column(name = "family_contact")
    private String familyContact;
    @Column(name = "family_condition")
    private String familyCondition;
    @Column(name = "transit_city")
    private String transitCity;
    @Column(name = "transit_method")
    private String transitMethod;
    @Column(name = "transit_no")
    private String transitNo;
    @Column(name = "transit_date")
    private String transitDate;
    @Column(name = "transit_wu_han")
    private String transitWuHan;
    @Column(name = "transit_hu_bei")
    private String transitHuBei;
    @Column(name = "transit_city_of_hu_bei")
    private String transitCityOfHuBei;
    @Column(name = "contact_history")
    private String contactHistory;
    @Column(name = "body_history")
    private String bodyHistory;
    @Column(name = "other_body_history")
    private String otherBodyHistory;
    @Column(name = "report_date")
    private String reportDate;

}
