package com.capture.packages.utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CmdUtils {

    public static boolean createWifi() {
        try {
            //password.length:[8, 64]
            Runtime.getRuntime().exec("elevate netsh wlan set hostednetwork mode=allow ssid=benedict key=12345678");
            //netsh wlan show drivers
            //支持的承载网络  : 否 (如果这里是 No，那么说明笔记本不支持热点功能)
            Runtime.getRuntime().exec("elevate netsh wlan start hostednetwork");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean shutdownWifi() {
        try {
            Runtime.getRuntime().exec("elevate netsh wlan stop hostednetwork");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 展示所有连接在某个网卡的所有 ip地址
     *
     * @return
     */
    public static List<String> displayAllDeviceIPAddressOnWifiConnection(String networkCard) {
        return deviceIps(networkCard);
    }

    /**
     * 获取所有链接在 网卡上的 ip地址（如果是wifi，一样是 网卡）
     *
     * @param networkCard
     * @return
     */
    private static List<String> deviceIps(String networkCard) {
        try {
            Process process = Runtime.getRuntime().exec("arp -a");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            List<String> ipConnected = new LinkedList<>();
            boolean findNetworkCard = false;
            while (reader.ready()) {
//                String ip = new String(reader.readLine().getBytes(Charset.forName("GB2312")), Charset.forName("UTF-8"));
                String ip = reader.readLine();
                if (StrUtils.isEmpty(ip))
                    continue;
                ip = ip.trim();
                if (ip.startsWith("Internet"))
                    continue;
                if (ip.contains("---")) {
                    String networkCardIp = ip.split("---")[0].split(":")[1].trim();
                    //"192.168.112.155"
                    if (networkCardIp.equals(networkCard)) {
                        findNetworkCard = true;
                    } else {
                        findNetworkCard = false;
                    }
                    continue;
                }
                if (findNetworkCard) {
                    ipConnected.add(ip.split(" ")[0]);
                }
            }
            return ipConnected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void cmd() throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \"F:\\如何成为 Java 高手\" && dir");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

    @Test
    public void adminCmd() throws IOException {
        Runtime.getRuntime().exec("elevate mspaint");
    }

    @Test
    public void split() {
        for (String s : "  192.168.112.57        b0-83-fe-bc-3c-b7     ��̬        ".trim().split(" ")) {
            System.out.println(s);
        }
    }
}