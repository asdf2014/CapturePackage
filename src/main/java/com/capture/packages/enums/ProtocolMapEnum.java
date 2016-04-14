package com.capture.packages.enums;

import com.capture.packages.model.IPv4HeaderInfos;
import com.capture.packages.model.TCPHeaderInfos;
import com.capture.packages.utils.StrUtils;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
public enum ProtocolMapEnum {
    IPv4_Header("IPv4 Header", IPv4HeaderInfos.class),
    TCP_Header("TCP Header", TCPHeaderInfos.class);

    private String protocolName;
    private Class protocolClass;

    ProtocolMapEnum(String protocolName, Class protocolClass) {
        this.protocolName = protocolName;
        this.protocolClass = protocolClass;
    }

    public static Class getPortocolClass(String protocolName) {
        if (StrUtils.isEmpty(protocolName))
            return null;
        for (ProtocolMapEnum protocolMapEnum : ProtocolMapEnum.values()) {
            if (protocolMapEnum.equals(protocolMapEnum.getProtocolName()))
                return protocolMapEnum.getProtocolClass();
        }
        return null;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public Class getProtocolClass() {
        return protocolClass;
    }
}
