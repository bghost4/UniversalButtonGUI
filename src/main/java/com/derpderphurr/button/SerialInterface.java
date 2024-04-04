package com.derpderphurr.button;

import com.derpderphurr.button.action.Action;
import com.fazecast.jSerialComm.SerialPort;

import java.nio.ByteBuffer;
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
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,5000,300);
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_CTS_ENABLED|SerialPort.FLOW_CONTROL_RTS_ENABLED|SerialPort.FLOW_CONTROL_DTR_ENABLED);
        serialPort.setNumDataBits(8);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(1);
        return true;
    }

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
        //read 1 bytes

//        bytes = 0;
//        while(bytes < 1) {
//            bytes = serialPort.readBytes(report_bytes, 1);
//        }

//        int rlength = report_bytes[0] & 0xFF;
//        System.out.printf("Total Bytes Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),rlength);

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

    public void putPressBuffer(byte[] stuff) {
        ByteBuffer buf = ByteBuffer.allocate(3);
        byte[] buffer = new byte[64];
        //ByteBuffer buf = ByteBuffer.allocate(3+ stuff.length);
        buf.put("PP".getBytes());
        buf.put((byte)stuff.length);
        //buf.put(stuff);
        System.out.println("Sending Bytes: ");
        System.out.println(HexFormat.of().formatHex(buf.array()));
        int bytes = serialPort.writeBytes(buf.array(),buf.array().length);
        System.out.printf("Send %d Bytes\n",bytes);
        //read 1 bytes
        byte[] report_bytes = new byte[1];
        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        System.out.printf("Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);
        int rlength = report_bytes[0] & 0xFF;
        System.out.printf("Expecting %d Bytes Back\n",rlength);
        bytes = 0;
        while(bytes < rlength) {
            bytes += serialPort.readBytes(buffer,(rlength-bytes));
        }
        System.out.println("Echo RECV Buffer: ");
        System.out.println(HexFormat.of().formatHex(buffer,0,bytes));
        //bytes = serialPort.writeBytes(stuff,stuff.length);
        //System.out.printf("Send %d Bytes\n",bytes);
        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        System.out.printf("Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);
        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        System.out.printf("Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);
    }

    public int readData(int size,byte[] buf) {
        return serialPort.readBytes(buf,size);
    }

    public byte[] getPressAction() {

        int total_bytes = 0;

        byte[] buffer = new byte[64];

        serialPort.writeBytes("GP".getBytes(), 2);

        int bytes = serialPort.readBytes(buffer, 64);
        total_bytes += bytes;
        System.out.printf("Got %d bytes\n", bytes);

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
