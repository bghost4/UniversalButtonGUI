package com.derpderphurr.button;

import com.derpderphurr.button.action.Action;
import com.fazecast.jSerialComm.SerialPort;

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

    public byte[] getPressAction() {

        serialPort.writeBytes("GP".getBytes(),2);
        byte[] buffer = new byte[64];
        int bytes = serialPort.readBytes(buffer,64);
        System.out.printf("Got %d bytes\n",bytes);
        return buffer;
    }

    public List<Action> getClockWiseAction() {
        return null;
    }

    public List<Action> getAntiClockWiseAction() {
        return null;
    }

}
