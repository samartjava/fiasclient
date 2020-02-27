package com.groupstp.fiasclient.web.screens;

import com.groupstp.fiasclient.entity.FiasCity;
import com.groupstp.fiasclient.entity.FiasHouse;
import com.groupstp.fiasclient.entity.FiasRegion;
import com.groupstp.fiasclient.service.FiasService;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Datafromfias extends AbstractWindow {

    @Inject
    private LookupField regionsLkf;
    @Inject
    private LookupField citiesLkf;
    @Inject
    private GroupTable<FiasHouse> addressesTbl;
    @Inject
    private FiasService fiasService;
    @Inject
    private CollectionDatasource<FiasRegion, UUID> fiasRegionsDs;
    @Inject
    private CollectionDatasource<FiasCity, UUID> fiasCitiesDs;
    @Inject
    private GroupDatasource<FiasHouse, UUID> fiasHousesDs;

    private static final Logger log = LoggerFactory.getLogger(Datafromfias.class);

    @Override
    public void ready() {
        refreshFiasRegionsDs();
    }

    private void refreshFiasRegionsDs() {
        fiasRegionsDs.clear();
        List<FiasRegion> fiasRegions = fiasService.getListFiasRegions();
        fiasRegions.forEach(fiasRegion -> fiasRegionsDs.includeItem(fiasRegion));
        regionsLkf.setOptionsDatasource(fiasRegionsDs);
        fiasRegionsDs.addItemChangeListener(e -> {
            fiasCitiesDs.clear();
            List<FiasCity> fiasCities = fiasService.getListFiasCitiesByRegion(fiasRegionsDs.getItem());
            fiasCities.forEach(fiasCity -> fiasCitiesDs.includeItem(fiasCity));
            citiesLkf.setOptionsDatasource(fiasCitiesDs);
        });
        fiasCitiesDs.addItemChangeListener(e -> {
            fiasHousesDs.clear();
            List<FiasHouse> fiasHouses = fiasService.getListFiasHouseByCity(fiasCitiesDs.getItem());
            fiasHouses.forEach(fiasHouse -> fiasHousesDs.includeItem(fiasHouse));
            addressesTbl.setDatasource(fiasHousesDs);
        });
    }
}