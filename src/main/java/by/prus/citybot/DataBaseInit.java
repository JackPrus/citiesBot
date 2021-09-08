package by.prus.citybot;

import by.prus.citybot.model.CityInfo;
import by.prus.citybot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBaseInit {

    @Autowired
    CityRepository cityRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event){

        List<CityInfo> allRecords = cityRepository.findAll();
        if (allRecords.size()<5){
            List<CityInfo> citiesList = getCitiesList();
            for (CityInfo cityInfo : citiesList){
                cityRepository.save(cityInfo);
            }
        }

    }


    public List<CityInfo> getCitiesList (){

        List<CityInfo> cityInfoList = new ArrayList<>();

        CityInfo kyiv = new CityInfo();
        kyiv.setName("Киев");
        kyiv.setInfo("Киево-Печерская лавра XI века – это монастырь и центр паломничества. На его территории возвышаются несколько церквей, увенчанных золотыми куполами.");

        CityInfo moscow = new CityInfo();
        moscow.setName("Москва");
        moscow.setInfo("Кремль – резиденция российского президента. На ее территории можно посетить Оружейную палату, где выставляются драгоценные предметы, принадлежавшие царской семье.");

        CityInfo riga = new CityInfo();
        riga.setName("Рига");
        riga.setInfo("Рига славится своими деревянными зданиями и домами в югендстиле (он же стиль модерн). ");

        CityInfo vilnus = new CityInfo();
        vilnus.setName("Вильнюс");
        vilnus.setInfo("В средневековом Старом городе с мощенными брусчаткой улицами, где также расположены кафедральный собор Святого Станислава в неоклассическом стиле и готический храм Святой Анны. Острая брама (или ворота Аушрос), где хранится чудотворный образ Матери Божией Остробрамской, когда-то была частью городской крепостной стены");

        CityInfo warsaw = new CityInfo();
        warsaw.setName("Варшава");
        warsaw.setInfo("В самом сердце Старого города, практически полностью разрушенного во время Второй мировой войны и восстановленного в послевоенные годы, лежит Рыночная площадь.");

        CityInfo minsk = new CityInfo();
        minsk.setName("Минск");
        minsk.setInfo("На немигу... если встретите милицию - лучше обойдите ее");

        cityInfoList.add(kyiv);
        cityInfoList.add(moscow);
        cityInfoList.add(riga);
        cityInfoList.add(vilnus);
        cityInfoList.add(warsaw);
        cityInfoList.add(minsk);

        return cityInfoList;
    }


}
