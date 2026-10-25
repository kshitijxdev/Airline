package com.shukla.service;

import com.shukla.mapper.CityMapper;
import com.shukla.model.City;
import com.shukla.payload.request.CityRequest;
import com.shukla.payload.response.CityResponse;
import com.shukla.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) throws Exception {
        if(cityRepository.existsByCityCode(request.getCityCode())){
            throw new Exception("city with given code already exist");
        }

        City city = CityMapper.toEntity(request);
        City result = cityRepository.save(city);
        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        City city = cityRepository.findById(id)
                .orElseThrow(()->
                        new Exception("city not exist with given id"));
        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(()-> new
                Exception("city not exist with given id"));

        if(cityRepository.existsByCityCode(request.getCityCode())){
            throw new Exception("city with given code already exists");
        }

        City updateCity =
                cityRepository
                        .save(CityMapper.updateEntity(city,request));

        return CityMapper.toResponse(updateCity);
    }

    @Override
    public void deleteCity(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(()->
                new Exception("city not exists with given id"));

        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository
                .searchByKeyword(keyword,pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository
                .findByCountryCodeIgnoreCase(countryCode,pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
