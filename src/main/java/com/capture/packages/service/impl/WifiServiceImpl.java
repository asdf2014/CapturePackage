package com.capture.packages.service.impl;

import com.capture.packages.service.IWifiService;
import com.capture.packages.utils.CmdUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Benedict Jin on 2016/4/15.
 */
@Service("wifiService")
public class WifiServiceImpl implements IWifiService {

    @Override
    public boolean openWifi() {
        return CmdUtils.createWifi();
    }

    @Override
    public boolean closeWifi() {
        return CmdUtils.shutdownWifi();
    }
}
