package com.groupstp.fiasclient.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@MetaClass(name = "fiasclient$FiasElement")
public class FiasElement extends BaseUuidEntity {
    private static final long serialVersionUID = 5595496422372495795L;

    @MetaProperty
    protected String fiasId;

    @MetaProperty
    protected String name;

    public void setFiasId(String fiasId) {
        this.fiasId = fiasId;
    }

    public String getFiasId() {
        return fiasId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}