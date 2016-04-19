package com.capture.packages.controller;

import com.capture.packages.service.IWifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Benedict Jin on 2016/4/15.
 */
@Controller
@RequestMapping("/wifi")
public class WifiController {

    @Autowired
    private IWifiService wifiService;

    @RequestMapping(value = "/open", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String openWifi(HttpServletRequest request, Model model) {
        return wifiService.openWifi() ? "OPEN SUCCESS" : "OPEN FAILED";
    }

    @RequestMapping(value = "/close", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String closeWifi(HttpServletRequest request, Model model) {
        //这里 success 只是说明 整个流程是正确的，查看是否成功，是需要在 网络中心确认的
        //wifi开启之后，可以用 "Display Network Cards" 进行展示，找到对应的 index，
        //在 network.card.index中配置即可，过滤掉其他的网卡，只保留 wifi网卡 上的网络包，
        //还有 在"Those IP address of device that connected with wifi" 模块展示的，就是链接该 wifi网卡的 手机、电脑等设备的 ip信息
        return wifiService.closeWifi() ? "CLOSE SUCCESS" : "CLOSE FAILED";
    }

}
