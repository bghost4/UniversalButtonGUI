package com.derpderphurr.button;

import com.fazecast.jSerialComm.SerialPort;

import java.util.HexFormat;

public class ProtocolTest {

    public static void main(String[] args) {
        SerialInterface iface = new SerialInterface();
        iface.connect(SerialPort.getCommPort("/dev/ttyACM0"));
        System.out.println(HexFormat.of().formatHex(iface.getPressAction()));
    }

}
