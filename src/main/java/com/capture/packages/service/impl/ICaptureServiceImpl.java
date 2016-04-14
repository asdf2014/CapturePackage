package com.capture.packages.service.impl;

import com.capture.packages.model.IPv4HeaderInfos;
import com.capture.packages.service.ICaptureService;
import com.capture.packages.utils.PropUtils;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.*;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.ByteArrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service("captureService")
public class ICaptureServiceImpl implements ICaptureService {

    private static final String COUNT_KEY
            = org.pcap4j.sample.LoopRaw.class.getName() + ".count";
    private static final int COUNT
            = Integer.getInteger(COUNT_KEY, 5);

    private static final String READ_TIMEOUT_KEY
            = org.pcap4j.sample.LoopRaw.class.getName() + ".readTimeout";
    private static final int READ_TIMEOUT
            = Integer.getInteger(READ_TIMEOUT_KEY, 10); // [ms]

    private static final String SNAPLEN_KEY
            = org.pcap4j.sample.LoopRaw.class.getName() + ".snaplen";
    private static final int SNAPLEN
            = Integer.getInteger(SNAPLEN_KEY, 65536); // [bytes]

    @Autowired
    private PropUtils propUtils;

    @Override
    public IPv4HeaderInfos getPackageInfos() throws Exception {

        String filter = "";
        PcapNetworkInterface nif = null;
        try {
            List<PcapNetworkInterface> allDevs = null;
            try {
                allDevs = Pcaps.findAllDevs();
                int nifIdx = Integer.parseInt(propUtils.getProperty("network.card.index"));
                if (nifIdx < 0 || nifIdx >= allDevs.size()) {
                    return null;
                }
                nif = allDevs.get(nifIdx);
            } catch (PcapNativeException e) {
                throw new IOException(e.getMessage());
            }
        } catch (IOException e) {
            return null;
        }
        if (nif == null) {
            return null;
        }
        System.out.println(nif.getName() + "(" + nif.getDescription() + ")");

        final PcapHandle handle = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);

        if (filter.length() != 0) {
            handle.setFilter(
                    filter,
                    BpfCompileMode.OPTIMIZE
            );
        }

        RawPacketListener listener = new RawPacketListener() {
            @Override
            public void gotPacket(byte[] packet) {
                System.out.println(handle.getTimestamp());
                System.out.println(ByteArrays.toHexString(packet, " "));
            }
        };

        try {
            int reTry = Integer.parseInt(propUtils.getProperty("capture.package.retry.times"));
            IPv4HeaderInfos iPv4HeaderInfos = null;
            while (reTry > 0) {
                iPv4HeaderInfos = loopReceive(handle, listener);
                if (iPv4HeaderInfos != null)
                    return iPv4HeaderInfos;
                reTry--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handle.close();
        }
        return null;
    }

    /**
     * 获取网卡接受到的数据
     *
     * @param handle
     * @param listener
     * @throws PcapNativeException
     * @throws NotOpenException
     */
    private IPv4HeaderInfos loopReceive(PcapHandle handle, RawPacketListener listener) throws PcapNativeException, NotOpenException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            handle.loop(COUNT, listener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Packet packet = handle.getNextPacket();
        if (packet != null) {
            System.out.println("Package:");
            System.out.println("RowData " + ByteArrays.toHexString(packet.getRawData(), " "));
            System.out.println("Header " + ByteArrays.toHexString(packet.getHeader().getRawData(), " "));

            String infos = packet.getPayload().toString();
            String[] lines = infos.split("\\r\\n");
            if (lines.length != 31)
                return null;
            return new IPv4HeaderInfos(lines[1].trim(), lines[2].trim(), lines[3].trim(), lines[4].trim(),
                    lines[5].trim(), lines[6].trim(), lines[7].trim(), lines[8].trim(),
                    lines[9].trim(), lines[10].trim(), lines[11].trim(), lines[12].trim());
        }
        return null;
    }
}