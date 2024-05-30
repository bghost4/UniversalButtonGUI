package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.Action;
import com.derpderphurr.button.action.ConsumerControl;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.List;

public class EventList extends VBox {
    private final ListView<Action> actions = new ListView<>();

    public EventList() {

        getChildren().add(actions);
        VBox.setVgrow(actions, Priority.ALWAYS);
        HBox hb = new HBox();
        Button btnMoveDown = new Button("Down");
        Button btnMoveUp = new Button("Up");
        Button btnRemove = new Button("Delete");
        Button btnAddKeyboard = new Button("+Keyboard");
        Button btnAddMouse = new Button("+Mouse");
        Button btnAddConsumer = new Button("+Consumer");
        Button btnAddToggle = new Button("Toggle");
        hb.getChildren().addAll(btnMoveUp, btnAddKeyboard, btnAddToggle,btnAddConsumer, btnAddMouse, btnRemove, btnMoveDown);
        getChildren().add(hb);

        btnAddKeyboard.setOnAction(eh -> new KeyboardEditor().showAndWait().ifPresent(ka -> actions.getItems().add(ka)));
        btnAddConsumer.setOnAction(eh -> new ChoiceDialog<>(null,ConsumerControl.values).showAndWait().ifPresent(cc -> actions.getItems().add(cc)));
        btnAddMouse.setOnAction(eh -> new MouseEditor().showAndWait().ifPresent(ma -> actions.getItems().add(ma)));
        btnAddToggle.setOnAction(eh -> new ToggleEditor().showAndWait().ifPresent(ma -> actions.getItems().add(ma)));

        actions.setCellFactory(view -> new ActionListCell());

        btnMoveUp.setOnAction(eh -> {
            int selectedIndex = actions.getSelectionModel().getSelectedIndex();
            if(selectedIndex > 0) {
                Collections.swap(actions.getItems(),selectedIndex,selectedIndex-1);
            }
        });
        btnMoveDown.setOnAction(eh -> {
            int selectedIndex = actions.getSelectionModel().getSelectedIndex();
            if(selectedIndex < actions.getItems().size()-1) {
                Collections.swap(actions.getItems(),selectedIndex,selectedIndex+1);
            }
        });
        btnRemove.setOnAction(eh -> {
                    int selectedIndex = actions.getSelectionModel().getSelectedIndex();
                    actions.getItems().remove(selectedIndex);
                }
        );

    }

    public List<? extends Action> getActions() {
        return actions.getItems().stream().sequential().toList();
    }



}
