package com.derpderphurr.button;

import com.fazecast.jSerialComm.SerialPort;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class ProtocolTest {

    public static void main(String[] args) {
        SerialInterface iface = new SerialInterface();
        iface.connect(SerialPort.getCommPort("/dev/ttyACM0"));
        System.out.println(HexFormat.of().formatHex(iface.getPressAction()));
        String stuff = "abcdefg";
        iface.putPressBuffer(stuff.getBytes(StandardCharsets.US_ASCII));
        System.out.printf("Available: %d\n",iface.available());
        System.out.println(HexFormat.of().formatHex(iface.getPressAction()));
    }

}
