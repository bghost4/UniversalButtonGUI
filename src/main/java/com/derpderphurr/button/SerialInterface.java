package com.derpderphurr.button;

import com.fazecast.jSerialComm.SerialPort;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.nio.ByteBuffer;
import java.util.HexFormat;
import java.util.function.Consumer;

public class SerialInterface {
    private SerialPort serialPort;
    public enum EventType { CLOCKWISE,ANTICLOCKWISE,PRESS }
    private final SimpleObjectProperty<Consumer<EventType>> handler = new SimpleObjectProperty<>((e) -> {});
    private final SimpleBooleanProperty connected = new SimpleBooleanProperty(false);

    public void storeToFlash() {
        serialPort.writeBytes("SS".getBytes(),2);
    }

    public void printFlash() {
        int bytes = 16 ;
        serialPort.writeBytes("GS".getBytes(),2);
        byte[] buffer = new byte[bytes];
        serialPort.readBytes(buffer,bytes);
        System.out.println(HexFormat.of().formatHex(buffer));
    }

    public void setOnEventComplete(Consumer<EventType> eventTypeConsumer) {
        handler.set(eventTypeConsumer);
    }

    public void checkForEvent() {
        int bytesAvailable = serialPort.bytesAvailable();
        if( bytesAvailable > 0) {
            byte[] bytes = new byte[bytesAvailable];
            serialPort.readBytes(bytes,bytesAvailable);
            for(byte b : bytes) {
                if(b == 'P') {
                    handler.get().accept(EventType.PRESS);
                } else if(b == 'C') {
                    handler.get().accept(EventType.CLOCKWISE);
                } else if(b == 'A') {
                    handler.get().accept(EventType.ANTICLOCKWISE);
                } else {
                    //Unknown Byte
                }
            }
        }
    }

    public boolean isConnected() {
        return connected.get();
    }

    public SimpleBooleanProperty connectedProperty() {
        return connected;
    }

    public void connect(SerialPort p) {
        this.serialPort = p;
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,5000,300);
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_CTS_ENABLED|SerialPort.FLOW_CONTROL_RTS_ENABLED|SerialPort.FLOW_CONTROL_DTR_ENABLED);
        serialPort.setNumDataBits(8);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(1);
        this.connected.set(true);
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



    public void putBuffer(EventType loc, byte[] stuff) {
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
        System.out.printf("Send %d Bytes\n",bytes);

        System.out.println(HexFormat.of().formatHex(stuff));
        bytes = serialPort.writeBytes(stuff,stuff.length);
        System.out.printf("Send %d Bytes\n",bytes);

        byte[] report_bytes = new byte[1];

        //Only One Report Now
        bytes = 0;
        while(bytes < 1) {
            bytes = serialPort.readBytes(report_bytes, 1);
        }
        System.out.printf("Report: %s - %d\n",HexFormat.of().formatHex(report_bytes),bytes);

    }

    public void readData(int size, byte[] buf) {
        serialPort.readBytes(buf, size);
    }

    public byte[] getAction(EventType loc) {

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

        //TODO Decode these and put them in their respective slots

        //System.out.printf("Got %d bytes\n", bytes);

        return buffer;
    }

    public int available() {
        return serialPort.bytesAvailable();
    }

}
