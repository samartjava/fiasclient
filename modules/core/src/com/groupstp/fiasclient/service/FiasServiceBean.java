package com.groupstp.fiasclient.service;

import com.groupstp.fiasclient.config.FiasConfig;
import com.groupstp.fiasclient.entity.FiasCity;
import com.groupstp.fiasclient.entity.FiasHouse;
import com.groupstp.fiasclient.entity.FiasRegion;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Resources;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service(FiasService.NAME)
public class FiasServiceBean implements FiasService {
    @Inject
    private Resources resources;
    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private FiasConfig fiasConfig;

    @Override
    public List<FiasRegion> getListFiasRegions() {
        List<FiasRegion> regions = new ArrayList<>();
        String sqlString = resources.getResourceAsString("com/groupstp/fiasclient/core/sql/get-fias-regions.sql");
        final List<Object[]> resultList = persistence.callInTransaction(fiasConfig.getDataStoreName(), em ->
                em.createNativeQuery(sqlString)
                        .getResultList());
        for (Object[] result : resultList) {
            FiasRegion fiasRegion = dataManager.create(FiasRegion.class);
            fiasRegion.setFiasId(result[0].toString());
            fiasRegion.setName(result[1].toString());
            regions.add(fiasRegion);
        }
        return regions;
    }

    @Override
    public List<FiasCity> getListFiasCitiesByRegion(FiasRegion fiasRegion) {
        String regionFiasId = fiasRegion.getFiasId();
        return getListFiasCitiesByRegion(regionFiasId);
    }

    @Override
    public List<FiasCity> getListFiasCitiesByRegion(String regionFiasId) {
        List<FiasCity> cities = new ArrayList<>();
        String sqlString = resources.getResourceAsString("com/groupstp/fiasclient/core/sql/get-fias-cities-by-region.sql");
        final List<Object[]> resultList = persistence.callInTransaction(fiasConfig.getDataStoreName(), em ->
                em.createNativeQuery(sqlString)
                        .setParameter("regionCode", getRedionCode(regionFiasId))
                        .getResultList());
        for (Object[] result : resultList) {
            FiasCity fiasCity = dataManager.create(FiasCity.class);
            fiasCity.setFiasId(result[0].toString());
            fiasCity.setName(result[1].toString());
            cities.add(fiasCity);
        }
        return cities;
    }

    @Override
    public List<FiasHouse> getListFiasHouseByCity(String cityFiasId) {
        List<FiasHouse> houses = new ArrayList<>();
        Pair<String, String> regionCodeAndFiasId = getRegionFiasIdAndCodeByCity(cityFiasId);
        String sqlString = resources.getResourceAsString("com/groupstp/fiasclient/core/sql/get-fias-addresses-by-city.sql")
                .replace("***house***", "house" + regionCodeAndFiasId.getValue());
        final List<Object[]> resultList = persistence.callInTransaction(fiasConfig.getDataStoreName(), em ->
                em.createNativeQuery(sqlString)
                        .setParameter("cityFiasId", cityFiasId)
                        .setParameter("regionFiasId", regionCodeAndFiasId.getKey())
                        .getResultList());
        for (Object[] result : resultList) {
            FiasHouse fiasHouse = dataManager.create(FiasHouse.class);
            fiasHouse.setFiasId(result[0].toString());
            fiasHouse.setCityFiasId(result[2].toString());
            fiasHouse.setPostalCode(result[3].toString());
            fiasHouse.setAddress(result[4].toString());
            fiasHouse.setHouseNum(result[5].toString());
            fiasHouse.setBuildNum(result[6].toString());
            fiasHouse.setBuildNum(result[7].toString());
            houses.add(fiasHouse);
        }
        return houses;
    }

    @Override
    public List<FiasHouse> getListFiasHouseByCity(FiasCity fiasCity) {
        String cityFiasId = fiasCity.getFiasId();
        return getListFiasHouseByCity(cityFiasId);
    }

    //возвращает код региона из БД ФИАС по UUID региона
    private String getRedionCode(String uuidRegion) {
        return (String) persistence.callInTransaction(fiasConfig.getDataStoreName(), em -> em.createNativeQuery("select regioncode from addrobj where aoguid = ?aoguid")
                .setParameter("aoguid", uuidRegion)
                .getFirstResult());
    }

    private Pair<String, String> getRegionFiasIdAndCodeByCity(String cityFiasId) {
        Pair<String, String> pair = new Pair<>("", "");
        String sqlString = resources.getResourceAsString("com/groupstp/fiasclient/core/sql/get-fias-region-by-city.sql");
        final List<Object[]> resultList = persistence.callInTransaction(fiasConfig.getDataStoreName(), em ->
                em.createNativeQuery(sqlString)
                        .setParameter("cityFiasId", cityFiasId)
                        .getResultList());
        if (resultList.size() > 0) {
            Object[] result = resultList.get(0);
            pair = new Pair<>(result[0].toString(), result[1].toString());
        }
        return pair;
    }
}