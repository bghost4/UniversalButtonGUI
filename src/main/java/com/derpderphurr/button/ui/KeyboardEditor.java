package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.KeyboardSequence;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class KeyboardEditor extends ActionEditor<KeyboardSequence> {

    private final Label lblDisplay = new Label("[EMPTY]");
    private final Button btnEdit = new Button("edit");

    public KeyboardEditor() {
        HBox hbLayout = new HBox();
        hbLayout.getChildren().addAll(lblDisplay,btnEdit);
        getChildren().add(hbLayout);
    }

    @Override
    public void setValue(KeyboardSequence value) {

    }

    @Override
    public KeyboardSequence getValue() {
        return null;
    }
}
