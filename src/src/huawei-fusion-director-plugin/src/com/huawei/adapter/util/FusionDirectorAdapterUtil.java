/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.adapter.util;

import com.integrien.alive.common.adapter3.Logger;
import com.integrien.alive.common.adapter3.describe.AdapterDescribe;
import com.vmware.vrops.logging.AdapterLoggerFactory;

import java.io.File;

/**
 * Utility class
 *
 * @since 2019-02-18
 */
public class FusionDirectorAdapterUtil {
    private final Logger logger;

    public FusionDirectorAdapterUtil(AdapterLoggerFactory loggerFactory) {
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

        AdapterDescribe adapterDescribe = AdapterDescribe.make(getDescribeXmlLocation() + "describe.xml");
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
     * @return adapter path
     */
    public static String getAdapterFolder() {
        return getAdapterHome() + File.separator + "FusionDirectorAdapter" + File.separator;
    }

    /**
     * Returns the adapters conf folder.
     *
     * @return adapters conf folder
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
