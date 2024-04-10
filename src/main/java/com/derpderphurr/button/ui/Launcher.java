package com.derpderphurr.button.ui;

import com.derpderphurr.button.SerialInterface;
import com.derpderphurr.button.action.Action;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;


public class Launcher extends Application {

    private final SerialInterface si = new SerialInterface();
    private final EventList elACW = new EventList(),elCW = new EventList(),elPress = new EventList();
    private final ComboBox<SerialPort> cboSerialPorts = new ComboBox<>();

    public static void main(String[] args) { launch(args); }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //si.connect(SerialPort.getCommPort("/dev/ttyACM0"));


        HBox hbSerial = new HBox();
        Button btnRefreshPorts = new Button("Refresh");
        Button btnConnect = new Button("Connect");
        hbSerial.getChildren().addAll(cboSerialPorts,btnConnect,btnRefreshPorts);

        cboSerialPorts.getItems().setAll(Arrays.asList(SerialPort.getCommPorts()));

        btnRefreshPorts.setOnAction( eh -> {
            List<SerialPort> ports = Arrays.asList(SerialPort.getCommPorts());
            cboSerialPorts.getItems().setAll(ports);
        });

        btnConnect.setOnAction(eh -> {
            if(cboSerialPorts.getValue()!= null) {
                si.connect(cboSerialPorts.getValue());
            }
        });

        TitledPane tpACW = new TitledPane("Aniti-Clockwise",elACW);
        TitledPane tpPress = new TitledPane("Press",elPress);
        TitledPane tpCW = new TitledPane("Clockwise",elCW);

        Button btn = new Button("Push To Device");
        btn.disableProperty().bind(si.connectedProperty().not());

        btn.setOnAction(eh -> {
            pushActions(elACW, SerialInterface.BufferLocation.ANTICLOCKWISE);
            pushActions(elCW, SerialInterface.BufferLocation.CLOCKWISE);
            pushActions(elPress, SerialInterface.BufferLocation.PRESS);
        });

        HBox hbLists = new HBox();
        hbLists.disableProperty().bind(si.connectedProperty().not());
        hbLists.getChildren().addAll(tpACW,tpPress,tpCW);

        VBox vb = new VBox();
        vb.getChildren().add(hbSerial);
        vb.getChildren().add(hbLists);
        vb.getChildren().add(btn);
        Scene s = new Scene(vb);
        primaryStage.setTitle("Universal Button Controls");
        primaryStage.setScene(s);
        primaryStage.show();
    }

    public void pushActions(EventList el, SerialInterface.BufferLocation bl) {
        List<? extends Action> a = el.getActions();
        List<Byte> stuff = a.stream().flatMap(Action::toBytes).toList();
        byte[] raw = new byte[stuff.size()+1];
        for(int i=0; i < stuff.size(); i++) {
            raw[i] = stuff.get(i);
        }
        raw[stuff.size()] = (byte)0x00;
        System.out.println("Size: "+raw.length);
        si.putBuffer(bl,raw);
    }


}
