package com.capture.packages.service.impl;

import com.capture.packages.service.INetworkCardService;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
@Service("networkCardService")
public class NetworkCardServiceImpl implements INetworkCardService {

    @Override
    public List<PcapNetworkInterface> displayAllCards() {
        try {
            return Pcaps.findAllDevs();
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
