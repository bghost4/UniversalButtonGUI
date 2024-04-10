package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.KeyboardSequence;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class KeyboardEditor extends ActionEditor<KeyboardSequence> {

    private final Label lblSummary = new Label();

    private final SimpleObjectProperty<KeyboardSequence> keyboardSequence = new SimpleObjectProperty<>();

    private final KeyboardSubEditor myKeyboardSubEditor = new KeyboardSubEditor();

    public KeyboardEditor() {
        HBox hbLayout = new HBox();
        Button btnEdit = new Button("edit");
        btnEdit.setOnAction(eh -> {
            myKeyboardSubEditor.setValue(getValue());
            myKeyboardSubEditor.showAndWait().ifPresent(this::setValue);
        });
        hbLayout.getChildren().addAll(lblSummary, btnEdit);
        getChildren().add(hbLayout);
    }

    @Override
    public void setValue(KeyboardSequence value) {
        this.keyboardSequence.set(value);
        System.out.println("Keyboard Sequence Set Value: "+value.getElements());
        lblSummary.setText(value.toString());
    }

    @Override
    public KeyboardSequence getValue() {
        return keyboardSequence.get();
    }
}
