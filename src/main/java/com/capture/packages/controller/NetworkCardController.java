package com.capture.packages.controller;

import com.capture.packages.model.NetworkCardInfos;
import com.capture.packages.service.INetworkCardService;
import org.pcap4j.core.PcapAddress;
import org.pcap4j.core.PcapNetworkInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
@Controller
@RequestMapping("/network")
public class NetworkCardController {

    @Autowired
    private INetworkCardService networkCardService;

    @RequestMapping(value = "/cards", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public List<NetworkCardInfos> queryCapture(HttpServletRequest request, Model model) {
        List<PcapNetworkInterface> pcapNetworkInterfaces = networkCardService.displayAllCards();
        List<NetworkCardInfos> networkCardInfosList = new LinkedList<>();
        NetworkCardInfos networkCardInfos;
        for (PcapNetworkInterface pcapNetworkInterface : pcapNetworkInterfaces) {
            networkCardInfos = new NetworkCardInfos();
            networkCardInfos.setName(pcapNetworkInterface.getName());
            networkCardInfos.setDescription(pcapNetworkInterface.getDescription());
            List<PcapAddress> pcapAddresses = pcapNetworkInterface.getAddresses();
            networkCardInfos.setAddresses(
                    pcapAddresses == null || pcapAddresses.size() == 0 ? "" :
                            pcapAddresses.get(0).getAddress().toString());
            networkCardInfosList.add(networkCardInfos);
        }
        return networkCardInfosList;
    }

}

