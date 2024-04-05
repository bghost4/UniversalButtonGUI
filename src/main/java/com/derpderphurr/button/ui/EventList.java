package com.derpderphurr.button.ui;

import com.derpderphurr.button.SerialInterface;
import com.derpderphurr.button.action.Action;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class EventList extends VBox {
    private final ListView<ActionEditor> actions = new ListView<>();
    private final Button btnAddConsumer = new Button("+Consumer"),
            btnAddMouse = new Button("+Mouse"),
            btnAddKeyboard = new Button("+Keyboard"),
            btnRemove = new Button("Delete"),
            btnMoveUp = new Button("Up"),
            btnMoveDown = new Button("Down");

    public EventList() {

        getChildren().add(actions);
        VBox.setVgrow(actions, Priority.ALWAYS);
        HBox hb = new HBox();
        hb.getChildren().addAll(btnMoveUp,btnAddKeyboard,btnAddConsumer,btnAddMouse,btnRemove,btnMoveDown);
        getChildren().add(hb);
    }

    public void clear() {
        actions.getItems().clear();
    }

    public void addAll(List<Action> newActions) {
        actions.getItems().addAll(newActions.stream().map(Action::createEditor).toList());
    }



}
