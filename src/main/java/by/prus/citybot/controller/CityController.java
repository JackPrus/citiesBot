package by.prus.citybot.controller;

import by.prus.citybot.exception.CityServiceException;
import by.prus.citybot.model.CityDTO;
import by.prus.citybot.model.OperationStatusModel;
import by.prus.citybot.model.ResponseMessage;
import by.prus.citybot.service.Bot;
import by.prus.citybot.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(
        value = "/cities",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
//http://localhost:8080/t.me/CitySomeInfoBot/cities
public class CityController {

    @Autowired
    Bot bot;
    @Autowired
    CityService cityService;

//    @GetMapping(path="{id}")
//    public CityDTO getCityById (@PathVariable Long id){
//
//        CityDTO returnValue = cityService.findById(id);
//        if (returnValue==null){
//            throw new CityServiceException(ResponseMessage.NO_RECORD_FOUND.getResponseMessage());
//        }
//
//        return returnValue;
//
//    }

    @GetMapping(path="{name}")
    public CityDTO getCityByName (@PathVariable String name){

        CityDTO returnValue = cityService.findByName(name);
        if (returnValue==null){
            throw new CityServiceException(ResponseMessage.NO_RECORD_FOUND.getResponseMessage());
        }
        return returnValue;

    }

    @PostMapping()
    public CityDTO createCity (@RequestBody CityDTO cityDTO){
        return cityService.createCity(cityDTO);
    }

    @PutMapping
    public CityDTO updateCity(@RequestBody CityDTO cityDTO){
        return cityService.updateCity(cityDTO);
    }

    @DeleteMapping(path = "{id}")
    public OperationStatusModel deleteCity(@PathVariable Long id){
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(ResponseMessage.DELETE_OPERATION.getResponseMessage());
        cityService.deleteCity(id);
        returnValue.setOperationResult(ResponseMessage.STATUS_SUCCESS.getResponseMessage());
        return returnValue;
    }



}
