package com.capture.packages.service;

import org.pcap4j.core.PcapNetworkInterface;

import java.util.List;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
public interface INetworkCardService {


    List<PcapNetworkInterface> displayAllCards();
}
