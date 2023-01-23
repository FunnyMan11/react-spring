package ge.ibsu.demo.service;


import ge.ibsu.demo.dto.*;
import ge.ibsu.demo.entity.City;
import ge.ibsu.demo.entity.Customer;
import ge.ibsu.demo.repository.CityRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Transactional
    public City addCity(AddCityDTO addCityDTO) throws Exception {
        City city = new City();
        GeneralUtil.getCopyOf(addCityDTO, city);
        return cityRepository.save(city);
    }
    @Transactional
    public City editCity(Long id, AddCityDTO addCityDTO) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(() -> new Exception("city_not_found"));
        GeneralUtil.getCopyOf(addCityDTO, city);
        return cityRepository.save(city);
    }
    public Slice<City> search(SearchCity searchCity, Paging paging){
        String cityName = null;
        if(searchCity.getCityName() != null && !searchCity.getCityName().equals("")){
            cityName = "%" + searchCity.getCityName() +"%";
        }
        Pageable pageable= PageRequest.of(paging.getPage() - 1,paging.getSize(), Sort.by(Sort.Direction.ASC, "createDate"));
        return cityRepository.search(cityName,pageable);

    }
}

