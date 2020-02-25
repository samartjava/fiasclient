package com.groupstp.fiasclient.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s -> %s|name,fiasId")
@MetaClass(name = "fiasclient$FiasRegion")
public class FiasRegion extends FiasElement {
    private static final long serialVersionUID = -3981485317687975250L;

}