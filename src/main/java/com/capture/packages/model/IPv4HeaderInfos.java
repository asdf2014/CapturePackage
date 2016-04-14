package com.capture.packages.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by Benedict Jin on 2016/4/13.
 */
public class IPv4HeaderInfos {

//[IPv4 Header (20 bytes)]
//   Version: 4 (IPv4)
//   IHL: 5 (20 [bytes])
//   TOS: [precedence: 0 (Routine)] [tos: 0 (Default)] [mbz: 0]
//   Total length: 127 [bytes]
//   Identification: 41599
//   Flags: (Reserved, Don't Fragment, More Fragment) = (false, true, false)
//   Fragment offset: 0 (0 [bytes])
//   TTL: 51
//   Protocol: 6 (TCP)
//   Header checksum: 0x298b
//   Source address: /103.233.81.99
//   Destination address: /192.168.1.122

    private String version;
    private String ihl;
    private String tos;
    private String totalLength;
    private String identification;
    private String reservedFlag;
    //    private boolean dontFragmentFlag;
//    private boolean moreFragmentFlag;
    private String fragmentOffset;
    private String ttl;
    private String protocol;
    private String headerChecksum;
    private String srcAddr;
    private String dstAddr;
//    private List<IpV4Packet.IpV4Option> options;
//    private byte[] padding;


    public IPv4HeaderInfos() {
    }

    public IPv4HeaderInfos(String version, String ihl, String tos, String totalLength, String identification, String reservedFlag, String fragmentOffset, String ttl, String protocol, String headerChecksum, String srcAddr, String dstAddr) {
        this.version = version;
        this.ihl = ihl;
        this.tos = tos;
        this.totalLength = totalLength;
        this.identification = identification;
        this.reservedFlag = reservedFlag;
        this.fragmentOffset = fragmentOffset;
        this.ttl = ttl;
        this.protocol = protocol;
        this.headerChecksum = headerChecksum;
        this.srcAddr = srcAddr;
        this.dstAddr = dstAddr;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIhl() {
        return ihl;
    }

    public void setIhl(String ihl) {
        this.ihl = ihl;
    }

    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getReservedFlag() {
        return reservedFlag;
    }

    public void setReservedFlag(String reservedFlag) {
        this.reservedFlag = reservedFlag;
    }

    public String getFragmentOffset() {
        return fragmentOffset;
    }

    public void setFragmentOffset(String fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHeaderChecksum() {
        return headerChecksum;
    }

    public void setHeaderChecksum(String headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public String getDstAddr() {
        return dstAddr;
    }

    public void setDstAddr(String dstAddr) {
        this.dstAddr = dstAddr;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
