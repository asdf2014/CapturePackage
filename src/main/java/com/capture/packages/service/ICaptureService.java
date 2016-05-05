package com.capture.packages.service;

import com.capture.packages.model.IPv4HeaderInfos;
import com.capture.packages.model.StoreDao;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
public interface ICaptureService {

    IPv4HeaderInfos getPackageInfos() throws Exception;

    StoreDao storeCapturePackage();
}
