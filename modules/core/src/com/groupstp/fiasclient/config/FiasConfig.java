package com.groupstp.fiasclient.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

@Source(type = SourceType.DATABASE)
public interface FiasConfig extends Config {
    @Property("fias.dataStoreName")
    @Default("fiasFnsDs")
    String getDataStoreName();
}
