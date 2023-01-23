package ge.ibsu.demo.controllers;


import ge.ibsu.demo.dto.*;
import ge.ibsu.demo.entity.City;
import ge.ibsu.demo.entity.Customer;
import ge.ibsu.demo.service.CityService;
import ge.ibsu.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @ResponseBody
    @RequestMapping(value = "/all", method  = RequestMethod.GET, produces = {"application/json"})
    public List<City> getAll(){
        return cityService.getAllCity();
    }
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public City getCity(@PathVariable Long id){
        return cityService.getCity(id);
    }
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public City addCity(@RequestBody AddCityDTO rd) throws Exception {
        GeneralUtil.checkRequiredProperties(rd, Arrays.asList("city_id", "city"));
        return cityService.addCity(rd);
    }
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public City editCity(@PathVariable Long id, @RequestBody AddCityDTO addCityDTO) throws Exception {
        GeneralUtil.checkRequiredProperties(addCityDTO, Arrays.asList("city_id", "city"));
        return cityService.editCity(id, addCityDTO);
    }
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<City> searchCity(@RequestBody RequestObject<SearchCity> rd){
        return cityService.search(rd.getData(),rd.getPaging());
    }

}
