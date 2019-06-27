package com.huawei.fd.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class NoSSLHttpRequestFactory extends SimpleClientHttpRequestFactory {

    private static final Logger LOGGER = Logger.getLogger(NoSSLHttpRequestFactory.class.getSimpleName());

    static {
        try {
            SSLUtil.turnOffSslChecking();
        } catch (KeyManagementException e) {
            LOGGER.info(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {

        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        }
        super.prepareConnection(connection, httpMethod);
    }

    static class SSLUtil {

        private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };

        public static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException {
            // Install the all-trusting trust manager
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }

        public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
            // Return it to the initial state (discovered by reflection, now
            // hardcoded)
            SSLContext.getInstance("SSL").init(null, null, null);
        }

        private SSLUtil() {
            throw new UnsupportedOperationException("Do not instantiate libraries.");
        }
    }

}
