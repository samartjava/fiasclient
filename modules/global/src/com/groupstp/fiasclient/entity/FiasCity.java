package com.groupstp.fiasclient.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s -> %s|name,fiasId")
@MetaClass(name = "fiasclient$FiasCity")
public class FiasCity extends FiasElement {
    private static final long serialVersionUID = 1573288553591405572L;

}