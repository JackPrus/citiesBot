package by.prus.citybot.repository;

import by.prus.citybot.model.CityInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityInfo, Long> {

    CityInfo findByName(String name);
    List<CityInfo> findAll();

}
