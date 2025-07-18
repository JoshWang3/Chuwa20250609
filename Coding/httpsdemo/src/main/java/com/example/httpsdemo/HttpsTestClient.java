package com.example.httpsdemo;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.X509Certificate;

public class HttpsTestClient {

    public static void main(String[] args) {
        System.out.println("=== Testing HTTPS Connection ===\n");

        // Test 1: Without certificate trust
        System.out.println("Test 1: Calling HTTPS endpoint without trusting certificate...");
        try {
            URL url = new URL("https://localhost:8443/api/hello");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            System.out.println("Success! This shouldn't happen with self-signed cert.");

        } catch (Exception e) {
            System.out.println("Failed (Expected): " + e.getMessage());
            System.out.println("This is expected because the self-signed certificate is not trusted.\n");
        }

        // Test 2: With trust all certificates
        System.out.println("Test 2: Calling HTTPS endpoint with trust all certificates...");
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            URL url = new URL("https://localhost:8443/api/hello");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            System.out.println("Success! Connection established by trusting all certificates.");

        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}