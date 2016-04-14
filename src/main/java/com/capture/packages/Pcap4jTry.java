package com.capture.packages;

import com.capture.packages.model.IPv4HeaderInfos;
import com.capture.packages.model.TCPHeaderInfos;
import com.sun.jna.Platform;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.*;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.ByteArrays;
import org.pcap4j.util.NifSelector;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Pcap4jTry {

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

    public static void main(String[] args) throws PcapNativeException, NotOpenException, IllegalAccessException, InstantiationException, InvocationTargetException {

        String filter = args.length != 0 ? args[0] : "";

        System.out.println(COUNT_KEY + ": " + COUNT);
        System.out.println(READ_TIMEOUT_KEY + ": " + READ_TIMEOUT);
        System.out.println(SNAPLEN_KEY + ": " + SNAPLEN);
        System.out.println("\n");

        PcapNetworkInterface nif;
        try {
            nif = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (nif == null) {
            return;
        }

        System.out.println(nif.getName() + "(" + nif.getDescription() + ")");

        final PcapHandle handle
                = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);

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
            while (true) {
                loopReceive(handle, listener);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            handle.close();
        }
    }

    /**
     * 获取网卡接受到的数据
     *
     * @param handle
     * @param listener
     * @throws PcapNativeException
     * @throws NotOpenException
     */
    private static void loopReceive(PcapHandle handle, RawPacketListener listener) throws PcapNativeException, NotOpenException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            handle.loop(COUNT, listener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PcapStat ps = handle.getStats();

        Packet packet = handle.getNextPacket();
        if (packet != null) {
            System.out.println("Package:");
            System.out.println("RowData " + ByteArrays.toHexString(packet.getRawData(), " "));
            System.out.println("Header " + ByteArrays.toHexString(packet.getHeader().getRawData(), " "));

            String infos = packet.getPayload().toString();
            String[] lines = infos.split("\\r\\n");
            if (lines.length != 31)
                return;
            IPv4HeaderInfos iPv4HeaderInfos = new IPv4HeaderInfos(lines[1], lines[2], lines[3], lines[4],
                    lines[5], lines[6], lines[7], lines[8],
                    lines[9], lines[10], lines[11], lines[12]);
            TCPHeaderInfos tcpHeaderInfos = new TCPHeaderInfos(lines[14], lines[15], lines[16], lines[17],
                    lines[18], lines[19], lines[20], lines[21],
                    lines[22], lines[23], lines[24], lines[25],
                    lines[26], lines[27], lines[28]);
            String realData = lines[30];
            System.out.println("IPv4HeaderInfos: " + iPv4HeaderInfos);
            System.out.println("TCPHeaderInfos: " + tcpHeaderInfos);
            System.out.println("RealData: " + realData);
        }
        System.out.println("Time: " + handle.getTimestamp());
        System.out.println("ps_recv: " + ps.getNumPacketsReceived());
        System.out.println("ps_drop: " + ps.getNumPacketsDropped());
        System.out.println("ps_ifdrop: " + ps.getNumPacketsDroppedByIf());
        if (Platform.isWindows()) {
            System.out.println("bs_capt: " + ps.getNumPacketsCaptured());
        }
    }

}