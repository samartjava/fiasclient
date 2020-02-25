package com.groupstp.fiasclient.service;


import com.groupstp.fiasclient.entity.FiasCity;
import com.groupstp.fiasclient.entity.FiasHouse;
import com.groupstp.fiasclient.entity.FiasRegion;

import java.util.List;

public interface FiasService {
    String NAME = "fiasclient_FiasService";

    /**
     * возвращает список регионов из БД ФИАС
     *
     * @return список регионов
     */
    List<FiasRegion> getListFiasRegions();

    /**
     * возвращает List городов из БД ФИАС по региону
     *
     * @param fiasRegion регион
     * @return список городов
     */
    List<FiasCity> getListFiasCitiesByRegion(FiasRegion fiasRegion);

    /**
     * возвращает List городов из БД ФИАС по региону
     *
     * @param regionFiasId фиасID региона
     * @return список городов
     */
    List<FiasCity> getListFiasCitiesByRegion(String regionFiasId);

    /**
     * возвращает List домов из БД ФИАС по городу
     * @param cityFiasId фиасID города
     * @return список домов
     */
    List<FiasHouse> getListFiasHouseByCity(String cityFiasId);

    /**
     * возвращает List домов из БД ФИАС по городу
     * @param fiasCity город
     * @return список домов
     */
    List<FiasHouse> getListFiasHouseByCity(FiasCity fiasCity);
}