package com.example;

import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;

public class Program
{
    public static void main(String args[])
        throws java.security.KeyStoreException, java.security.KeyManagementException, java.security.NoSuchAlgorithmException, java.io.IOException
    {
        System.out.println("Hello world");

        KeyStore javaKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        SSLContext sslContext = SSLContexts.custom()
            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
            .build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
            .setSSLContext(sslContext);

        try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
            HttpGet request = new HttpGet("https://github.com/");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println(response.getStatusLine().getReasonPhrase());
            }
        }
    }
}