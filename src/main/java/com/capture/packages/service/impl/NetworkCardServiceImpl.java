package com.capture.packages.service.impl;

import com.capture.packages.model.WifiInfos;
import com.capture.packages.service.INetworkCardService;
import com.capture.packages.utils.CmdUtils;
import com.capture.packages.utils.PropUtils;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
@Service("networkCardService")
public class NetworkCardServiceImpl implements INetworkCardService {

    @Autowired
    private PropUtils propUtils;

    @Override
    public List<PcapNetworkInterface> displayAllCards() {
        try {
            return Pcaps.findAllDevs();
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WifiInfos> getDeviceIps() {
        List<WifiInfos> wifiInfosList = new LinkedList<>();
        WifiInfos wifiInfos;
        for (String ip : CmdUtils.displayAllDeviceIPAddressOnWifiConnection(propUtils.getProperty("network.card.wifi"))) {
            wifiInfos = new WifiInfos();
            wifiInfos.setDeviceIP(ip);
            wifiInfosList.add(wifiInfos);
        }
        return wifiInfosList;
    }

}
