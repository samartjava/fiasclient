package com.groupstp.fiasclient.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s -> %s|address,fiasId")
@MetaClass(name = "fiasclient$FiasHouse")
public class FiasHouse extends BaseUuidEntity {
    private static final long serialVersionUID = -8220607235439725392L;

    @MetaProperty
    protected String fiasId;

    @MetaProperty
    protected String cityFiasId;

    @MetaProperty
    protected String postalCode;

    @MetaProperty
    protected String address;

    @MetaProperty
    protected String houseNum;

    @MetaProperty
    protected String buildNum;

    @MetaProperty
    protected String strucNum;

    public void setFiasId(String fiasId) {
        this.fiasId = fiasId;
    }

    public String getFiasId() {
        return fiasId;
    }

    public void setCityFiasId(String cityFiasId) {
        this.cityFiasId = cityFiasId;
    }

    public String getCityFiasId() {
        return cityFiasId;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setStrucNum(String strucNum) {
        this.strucNum = strucNum;
    }

    public String getStrucNum() {
        return strucNum;
    }


}