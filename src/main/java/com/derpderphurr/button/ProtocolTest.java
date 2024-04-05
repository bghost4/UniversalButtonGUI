package com.derpderphurr.button;

import com.fazecast.jSerialComm.SerialPort;

import java.util.HexFormat;

public class ProtocolTest {

    public static void main(String[] args) {
        SerialInterface iface = new SerialInterface();
        iface.connect(SerialPort.getCommPort("/dev/ttyACM0"));
        System.out.println("Reading Press Buffer");
        System.out.println(HexFormat.of().formatHex(iface.getAction(SerialInterface.BufferLocation.PRESS)));

        System.out.println("Sending New Press Command");
        byte[] stuff = HexFormat.of().parseHex("a1070918060e2c1008");
        iface.putPressBufferOneGo(stuff);

        System.out.printf("Extra Available: %d\n", iface.available());
        int extra = iface.available();
        byte[] buffer = new byte[extra];

        iface.readData(extra,buffer);
        System.out.printf("Extra: %s\n",HexFormat.of().formatHex(buffer));

        System.out.println("Reading Back Hopefully Changed Press Buffer");
        System.out.println(HexFormat.of().formatHex(iface.getAction(SerialInterface.BufferLocation.PRESS)));
    }

}
