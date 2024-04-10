package com.derpderphurr.button.ui;

import com.derpderphurr.button.action.MouseAction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MouseEditor extends ActionEditor<MouseAction> {

    private final SimpleObjectProperty<MouseAction> value = new SimpleObjectProperty<>();
    private final MouseSubEditor subEditor = new MouseSubEditor();

    public MouseEditor() {
        HBox hbLayout = new HBox();
        Button btnEdit = new Button("Edit");
        btnEdit.setOnAction(eh -> {
                    if (value.get() != null) {
                        subEditor.setValue(value.get());
                    }
            subEditor.showAndWait().ifPresent(value::set);
                });
        Label lblDisplay = new Label();
        hbLayout.getChildren().addAll(lblDisplay,btnEdit);
        getChildren().addAll(hbLayout);
    }

    @Override
    public void setValue(MouseAction value) {
        this.value.set(value);
    }

    @Override
    public MouseAction getValue() {
        //get event from sub-editor
        return value.get();
    }
}
