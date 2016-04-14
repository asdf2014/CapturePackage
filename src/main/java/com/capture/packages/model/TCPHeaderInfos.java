package com.capture.packages.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by Benedict Jin on 2016/4/13.
 */
public class TCPHeaderInfos {

//    [TCP Header (20 bytes)]
//    Source port: 40115 (unknown)
//    Destination port: 64135 (unknown)
//    Sequence Number: 3685887993
//    Acknowledgment Number: 770515802
//    Data Offset: 5 (20 [bytes])
//    Reserved: 0
//    URG: false
//    ACK: true
//    PSH: true
//    RST: false
//    SYN: false
//    FIN: false
//    Window: 48
//    Checksum: 0x0a3b
//    Urgent Pointer: 0

    private String srcPort;
    private String dstPort;
    private String sequenceNumber;
    private String acknowledgmentNumber;
    private String dataOffset;
    private String reserved;
    private String urg;
    private String ack;
    private String psh;
    private String rst;
    private String syn;
    private String fin;
    private String window;
    private String checksum;
    private String urgentPointer;

    public TCPHeaderInfos(String srcPort, String dstPort, String sequenceNumber, String acknowledgmentNumber, String dataOffset, String reserved, String urg, String ack, String psh, String rst, String syn, String fin, String window, String checksum, String urgentPointer) {
        this.srcPort = srcPort;
        this.dstPort = dstPort;
        this.sequenceNumber = sequenceNumber;
        this.acknowledgmentNumber = acknowledgmentNumber;
        this.dataOffset = dataOffset;
        this.reserved = reserved;
        this.urg = urg;
        this.ack = ack;
        this.psh = psh;
        this.rst = rst;
        this.syn = syn;
        this.fin = fin;
        this.window = window;
        this.checksum = checksum;
        this.urgentPointer = urgentPointer;
    }

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getAcknowledgmentNumber() {
        return acknowledgmentNumber;
    }

    public void setAcknowledgmentNumber(String acknowledgmentNumber) {
        this.acknowledgmentNumber = acknowledgmentNumber;
    }

    public String getDataOffset() {
        return dataOffset;
    }

    public void setDataOffset(String dataOffset) {
        this.dataOffset = dataOffset;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getUrg() {
        return urg;
    }

    public void setUrg(String urg) {
        this.urg = urg;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getPsh() {
        return psh;
    }

    public void setPsh(String psh) {
        this.psh = psh;
    }

    public String getRst() {
        return rst;
    }

    public void setRst(String rst) {
        this.rst = rst;
    }

    public String getSyn() {
        return syn;
    }

    public void setSyn(String syn) {
        this.syn = syn;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getUrgentPointer() {
        return urgentPointer;
    }

    public void setUrgentPointer(String urgentPointer) {
        this.urgentPointer = urgentPointer;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
