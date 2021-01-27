/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Trust manager by thumbprint.
 *
 * @since 2019-02-18
 */
public class ThumbprintTrustManager implements javax.net.ssl.X509TrustManager {
    private static final String ERROR_MSG = "Server certificate chain is not trusted and thumbprint doesn't match";
    private static final Logger LOGGER = LogManager.getLogger(ThumbprintTrustManager.class);
    private static final Set<String> _thumbprints = new CopyOnWriteArraySet<>();

    /**
     * Adds the specified thumbprint to a thumbprint collection of valid thumbprints The thumbprint is
     * added only if not already present.
     *
     * @param thumbprint the thumbprint to be added to the collection
     * @return true if the thumbprint collection did not already contain the specified element
     */
    public boolean addThumbprint(String thumbprint) {
        return _thumbprints.add(thumbprint);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        for (X509Certificate cert : certs) {
            checkThumbprint(cert);
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        return;
    }

    /**
     * Extracts the thumbprint from the certificate and verifies that the thumbprint is part of the
     * known valid thumbprints
     *
     * @param cert the thumbprint to be verified
     * @throws CertificateException if the thumbrint is not part of the known thumbrints
     */
    public static void checkThumbprint(X509Certificate cert) throws CertificateException {
        String thumbprint = getThumbprint(cert);

        if (!_thumbprints.contains(thumbprint)) {
            LOGGER.error(ERROR_MSG);
            throw new CertificateException(ERROR_MSG);
        }
    }

    public static String getThumbprint(X509Certificate cert) throws CertificateException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] certBytes = cert.getEncoded();
            byte[] bytes = md.digest(certBytes);
            return convertHexToString(bytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(ERROR_MSG);
            throw new CertificateException(ERROR_MSG);
        }
    }

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    private static String convertHexToString(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static Set<String> getThumbprints() {
        return _thumbprints;
    }
}
