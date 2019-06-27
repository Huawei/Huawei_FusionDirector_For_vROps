/*
 * Copyright (c) 2014-2015 VMware, Inc. All rights reserved.
 */
package com.huawei.adapter.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.integrien.alive.common.adapter3.describe.AdapterDescribe;
import com.integrien.alive.common.util.InstanceLoggerFactory;

/**
 * Utility class
 */
public class FusionDirectorAdapterUtil {

    private final Logger logger;

    public FusionDirectorAdapterUtil(InstanceLoggerFactory loggerFactory) {
        this.logger = loggerFactory.getLogger(FusionDirectorAdapterUtil.class);
    }

    /**
     * Utility Method to create Adapter describe. Instance of AdapterDescribe is
     * created using describe.xml kept under config folder.
     *
     * @return instance of AdapterDescribe class {@link AdapterDescribe}
     */
    public AdapterDescribe createAdapterDescribe() {

        assert (logger != null);

        // AdapterDescribe has all static information about the adapter like
        // what
        // all resource kinds (object types)
        // are supported by the adapter, what all metrics are expected for those
        // resource kinds

        AdapterDescribe adapterDescribe = AdapterDescribe
                .make(getDescribeXmlLocation() + "describe.xml");
        if (adapterDescribe == null) {
            logger.error("Unable to load adapter describe");
        } else {
            logger.debug("Successfully loaded adapter");
        }
        return adapterDescribe;
    }

    /**
     * Method to return Adapter's home directory/folder
     *
     * @return Adapter home folder path
     */
    public static String getAdapterHome() {
        // intentionally left constant CommononalConstants.ADAPTER_HOME for
        // common.jar version compatibility
        String adapterHome = System.getProperty("ADAPTER_HOME");
        String collectorHome = System.getProperty("COLLECTOR_HOME");
        if (collectorHome != null) {
           adapterHome = collectorHome + File.separator + "adapters";
        }
        return adapterHome;
    }

    /**
     * Returns the adapters root folder.
     *
     * @return
     */
    public static String getAdapterFolder() {
        return getAdapterHome() + File.separator + "FusionDirectorAdapter"
                + File.separator;
    }

    /**
     * Returns the adapters conf folder.
     *
     * @return
     */
    public static String getConfFolder() {
             return getAdapterFolder() + "conf" + File.separator;
    }

    /**
     * Method to return describe XML location including the file name. It first
     * checks if
     *
     * @return describe XML location
     */
    public static String getDescribeXmlLocation() {

        String describeXML = null;
        String adapterHome = System.getProperty("ADAPTER_HOME");
        if (adapterHome == null) {
            describeXML = System.getProperty("user.dir") + File.separator;
        } else {
            describeXML = getConfFolder();
        }
        return describeXML;
    }
}
