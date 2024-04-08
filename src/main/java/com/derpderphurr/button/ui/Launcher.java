package com.derpderphurr.button.ui;

import com.derpderphurr.button.SerialInterface;
import com.derpderphurr.button.action.Action;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Launcher extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EventList l = new EventList();

        SerialInterface si = new SerialInterface();
        si.connect(SerialPort.getCommPort("/dev/ttyACM0"));

        Button btn = new Button("Push");
        btn.setOnAction(eh -> {
            List<Action> a = l.getActions();
            List<Byte> stuff = a.stream().flatMap(s -> s.toBytes()).toList();
            byte[] raw = new byte[stuff.size()+1];
            for(int i=0; i < stuff.size(); i++) {
                raw[i] = stuff.get(i);
            }
            raw[stuff.size()] = (byte)0x00;
            si.putBuffer(SerialInterface.BufferLocation.PRESS,raw);
        });

        VBox vb = new VBox();
        vb.getChildren().add(l);
        vb.getChildren().add(btn);
        Scene s = new Scene(vb);
        primaryStage.setTitle("Universal Button Controls");
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
