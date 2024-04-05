package com.derpderphurr.button.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Launcher extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {

       EventList l = new EventList();
        VBox vb = new VBox();
        vb.getChildren().add(l);
        Scene s = new Scene(vb);
        primaryStage.setTitle("Universal Button Controls");
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
