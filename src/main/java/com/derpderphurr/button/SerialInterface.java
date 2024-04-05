package com.derpderphurr.button;

import com.fazecast.jSerialComm.SerialPort;

import java.nio.ByteBuffer;
import java.util.HexFormat;

public class SerialInterface {
    private SerialPort serialPort;

    public enum BufferLocation { CLOCKWISE,ANTICLOCKWISE,PRESS };

    public boolean isConnected(){ return false;}
    public boolean connect(SerialPort p) {
        this.serialPort = p;

        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,5000,300);
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_CTS_ENABLED|SerialPort.FLOW_CONTROL_RTS_ENABLED|SerialPort.FLOW_CONTROL_DTR_ENABLED);
        serialPort.setNumDataBits(8);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(1);
        return true;
    }

    //For some reason, this one corrupts the input, and I haven't figured out why, makes me pisstified
    public void putPressBufferOneGo(byte[] stuff) {
        ByteBuffer buf = ByteBuffer.allocate(3+ stuff.length);
        byte[] report_bytes = new byte[1];
        byte[] buffer = new byte[64];

        buf.put("PP".getBytes());
        buf.put((byte)stuff.length);
        buf.put(stuff);

        System.out.println("Sending Bytes: ");
        System.out.println(HexFormat.of().formatHex(buf.array()));
        int bytes = serialPort.writeBytes(buf.array(),buf.array().length);
        System.out.printf("Send %d Bytes\n",bytes);

        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        int bsent = report_bytes[0] & 0xFF;
        System.out.printf("Bytes Sent Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);

        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        int btot = report_bytes[0] & 0xFF;
        System.out.printf("Bytes Total Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),btot);
    }



    public void putBuffer(BufferLocation loc,byte[] stuff) {
        ByteBuffer buf = ByteBuffer.allocate(3);

        switch(loc) {
            case PRESS -> buf.put("PP".getBytes());
            case CLOCKWISE -> buf.put("PC".getBytes());
            case ANTICLOCKWISE -> buf.put("PA".getBytes());
        }

        buf.put((byte)stuff.length);

        //System.out.println("Sending Bytes: ");
        System.out.println(HexFormat.of().formatHex(buf.array()));
        int bytes = serialPort.writeBytes(buf.array(),buf.array().length);
        //System.out.printf("Send %d Bytes\n",bytes);

        bytes = serialPort.writeBytes(stuff,stuff.length);
        //System.out.printf("Send %d Bytes\n",bytes);

        byte[] report_bytes = new byte[1];

        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        //System.out.printf("Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);
        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        //System.out.printf("Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);
    }

    public int readData(int size,byte[] buf) {
        return serialPort.readBytes(buf,size);
    }

    public byte[] getAction(BufferLocation loc) {

        int total_bytes = 0;

        byte[] buffer = new byte[64];

        String cmd  = switch(loc) {
            case PRESS -> "GP";
            case CLOCKWISE -> "GC";
            case ANTICLOCKWISE -> "GA";
        };

        serialPort.writeBytes(cmd.getBytes(), 2);

        //Maybe I should have it send the size back before the buffer this has to timeout
        int bytes = serialPort.readBytes(buffer, 64);
        total_bytes += bytes;
        //System.out.printf("Got %d bytes\n", bytes);

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
