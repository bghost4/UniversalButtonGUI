package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.KeyboardSequence;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class KeyboardEditor extends ActionEditor<KeyboardSequence> {

    private final TextFlow tfDisplay = new TextFlow();
    private final Button btnEdit = new Button("edit");

    public KeyboardEditor() {
        HBox hbLayout = new HBox();
        btnEdit.setOnAction(eh ->
            new KeyboardSubEditor().showAndWait().ifPresent(ks -> setValue(ks))
        );
        hbLayout.getChildren().addAll(tfDisplay,btnEdit);
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
