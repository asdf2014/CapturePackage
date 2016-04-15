package com.capture.packages.service;

import com.capture.packages.model.IPv4HeaderInfos;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
public interface ICaptureService {

    IPv4HeaderInfos getPackageInfos() throws Exception;

    boolean storeCapturePackage();
}
