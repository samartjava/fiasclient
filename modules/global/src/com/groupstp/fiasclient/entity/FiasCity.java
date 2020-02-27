package com.groupstp.fiasclient.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.MetaProperty;

@NamePattern("%s -> %s|name,fiasId")
@MetaClass(name = "fiasclient$FiasCity")
public class FiasCity extends FiasElement {
    private static final long serialVersionUID = 1573288553591405572L;

    @MetaProperty
    protected Long quantityOfHouses;

    public void setQuantityOfHouses(Long quantityOfHouses) {
        this.quantityOfHouses = quantityOfHouses;
    }

    public Long getQuantityOfHouses() {
        return quantityOfHouses;
    }


}