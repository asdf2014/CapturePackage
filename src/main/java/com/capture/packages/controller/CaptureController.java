package com.capture.packages.controller;

import com.capture.packages.model.IPv4HeaderInfos;
import com.capture.packages.service.ICaptureService;
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
@RequestMapping("/capture")
public class CaptureController {

    @Autowired
    private ICaptureService captureService;

    @RequestMapping(value = "/infos", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public List<IPv4HeaderInfos> queryCapture(HttpServletRequest request, Model model) {

        LinkedList<IPv4HeaderInfos> ips = new LinkedList<>();
        try {
            ips.add(captureService.getPackageInfos());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ips;
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String store(HttpServletRequest request, Model model) {
        return captureService.storeCapturePackage() ? "{'message':'存储成功'}" : "{'message':'存储失败'}";
    }

}
