package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityWithBranchResponse {
    private List<City> cities;
}
