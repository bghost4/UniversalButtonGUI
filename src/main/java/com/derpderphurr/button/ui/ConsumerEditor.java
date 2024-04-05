package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.ConsumerControl;
import javafx.scene.control.ComboBox;

public class ConsumerEditor extends ActionEditor<ConsumerControl> {

    private final ComboBox<ConsumerControl.ConsumerControlAction> action = new ComboBox<>();

    public ConsumerEditor() {
        //Initialization
        action.getItems().addAll(ConsumerControl.ConsumerControlAction.values());
        action.setPromptText("Select Control");

        //Layout
        this.getChildren().add(action);
    }

    @Override
    public void setValue(ConsumerControl value) {
        action.setValue(value.getAction());
    }

    @Override
    public ConsumerControl getValue() {
        return new ConsumerControl(action.getValue());
    }
}
