package com.capture.packages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class Http302Redirect {

    public static void main(String[] args) {

        try {
            String url = "http://weibo.com/1253711052";
            System.out.println("访问地址:" + url);

            Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,
                    new InetSocketAddress("127.0.0.1", 8888));
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl
                    .openConnection(proxy);
            conn.setRequestMethod("GET");

            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", "http://weibo.com");
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line).append("\r\n");
                line = reader.readLine();
            }
            reader.close();
            conn.disconnect();
            System.out.println(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}  