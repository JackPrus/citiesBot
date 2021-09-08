package by.prus.citybot.service;

import by.prus.citybot.model.CityDTO;

public interface CityService {

    CityDTO findById(Long id);
    CityDTO findByName (String name);
    CityDTO createCity (CityDTO cityDTO);
    CityDTO updateCity (CityDTO cityDTO);
    void deleteCity (Long id);

}
