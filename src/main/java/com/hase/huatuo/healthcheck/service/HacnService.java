package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.CitiesHacnRepository;
import com.hase.huatuo.healthcheck.dao.entity.CitiesHacn;
import com.hase.huatuo.healthcheck.model.City;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HacnService {

    @Autowired
    private CitiesHacnRepository citiesHacnRepository;

    @Autowired
    private Mapper mapper;

    public List<City>  getAllCitiesBranches() {
        List<City> cities = null;
        List<CitiesHacn> hacnCities = citiesHacnRepository.findAll();
        if (!CollectionUtils.isEmpty(hacnCities)) {
            cities = hacnCities.stream().map(hacnCity -> mapper.map(hacnCity, City.class)).collect(Collectors.toList());
        }
        return cities;
    }
}
