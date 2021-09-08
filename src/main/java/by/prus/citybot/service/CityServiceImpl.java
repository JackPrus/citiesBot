package by.prus.citybot.service;

import by.prus.citybot.exception.CityServiceException;
import by.prus.citybot.model.CityDTO;
import by.prus.citybot.model.CityInfo;
import by.prus.citybot.model.ResponseMessage;
import by.prus.citybot.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CityDTO findById(Long id) {

        Optional<CityInfo> cityInfo = cityRepository.findById(id);
        if (cityInfo.isEmpty()){
            throw new CityServiceException(ResponseMessage.NO_RECORD_FOUND.getResponseMessage());
        }
        return modelMapper.map(cityInfo.get(), CityDTO.class);
    }

    @Override
    public CityDTO findByName(String name) {
        CityInfo cityInfo = cityRepository.findByName(name);
        if (cityInfo==null){
            throw new CityServiceException(ResponseMessage.NO_RECORD_FOUND.getResponseMessage());
        }
        return modelMapper.map(cityInfo, CityDTO.class);
    }

    @Override
    public CityDTO createCity(CityDTO cityDTO) {

        if (!isValidCity(cityDTO)){
            throw new CityServiceException(ResponseMessage.MISSING_REQUIRED_FIELD.getResponseMessage());
        }

        if (isCityExist(cityDTO)){
            throw new CityServiceException(ResponseMessage.RECORD_ALREADY_EXISTS.getResponseMessage());
        }

        CityInfo cityToSave = modelMapper.map(cityDTO, CityInfo.class);
        CityInfo savedCity = cityRepository.save(cityToSave);

        return modelMapper.map(savedCity, CityDTO.class);
    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) {

        if (!isValidCity(cityDTO)){
            throw new CityServiceException(ResponseMessage.MISSING_REQUIRED_FIELD.getResponseMessage());
        }

        if (!isCityExist(cityDTO)){
            throw new CityServiceException(ResponseMessage.NO_RECORD_FOUND.getResponseMessage());
        }

        CityInfo cityToUpdate = cityRepository.findByName(cityDTO.getName());
        cityToUpdate.setInfo(cityDTO.getInfo());
        CityInfo updatedCity = cityRepository.save(cityToUpdate);

        return modelMapper.map(updatedCity, CityDTO.class);
    }

    @Override
    public void deleteCity(Long id) {
        Optional<CityInfo> cityInfoOptional = cityRepository.findById(id);
        if (cityInfoOptional.isEmpty()){
            throw new CityServiceException(ResponseMessage.NO_RECORD_FOUND.getResponseMessage());
        }
        cityRepository.delete(cityInfoOptional.get());
    }


    private boolean isValidCity (CityDTO cityDTO){
        return  cityDTO.getName() != null &&
                cityDTO.getInfo() != null &&
                !cityDTO.getName().isEmpty() &&
                !cityDTO.getInfo().isEmpty();
    }

    private boolean isCityExist (CityDTO cityDTO){
        CityInfo existedCity = cityRepository.findByName(cityDTO.getName());
        return existedCity != null;
    }

}
