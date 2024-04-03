package com.derpderphurr.button;

import com.derpderphurr.button.action.Action;
import com.fazecast.jSerialComm.SerialPort;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HexFormat;
import java.util.List;

public class SerialInterface {
    private SerialPort serialPort;

    public void setPress(List<Action> actions){ }
    public void setClockWise(List<Action> actions) {}
    public void setAntiClockWise(List<Action> actions) {}
    public boolean isConnected(){ return false;}
    public boolean connect(SerialPort p) {
        this.serialPort = p;

        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,1000,0);
        return true;
    }

    public void putPressBuffer(byte[] stuff) {
        ByteBuffer buf = ByteBuffer.allocate(stuff.length+3);
        buf.put("PP".getBytes());
        buf.put((byte)stuff.length);
        buf.put(stuff);
        System.out.println("Sending Bytes: ");
        System.out.println(HexFormat.of().formatHex(buf.array()));
        int bytes = serialPort.writeBytes(buf.array(),buf.array().length);
        System.out.printf("Send %d Bytes\n",bytes);
        //read 4 bytes
        byte[] report_bytes = new byte[1];
        int count = serialPort.readBytes(report_bytes,1);
        System.out.printf("Report: %s\n",HexFormat.of().formatHex(report_bytes));
    }
    public byte[] getPressAction() {

        int total_bytes = 0;

        byte[] buffer = new byte[64];

        while(total_bytes < 16) {
            serialPort.writeBytes("GP".getBytes(), 2);

            int bytes = serialPort.readBytes(buffer, 64);
            total_bytes += bytes;
            System.out.printf("Got %d bytes\n", bytes);
        }
        return buffer;
    }

    public int available() {
        return serialPort.bytesAvailable();
    }

    public byte[] getClockWiseAction() {
        serialPort.writeBytes("GC".getBytes(),2);
        byte[] buffer = new byte[64];
        int bytes = serialPort.readBytes(buffer,64);
        System.out.printf("Got %d bytes\n",bytes);
        return buffer;
    }

    public byte[] getAntiClockWiseAction() {
        serialPort.writeBytes("GA".getBytes(),2);
        byte[] buffer = new byte[64];
        int bytes = serialPort.readBytes(buffer,64);
        System.out.printf("Got %d bytes\n",bytes);
        return buffer;
    }

}
